package com.webhook1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.webhook1.dto.PaymentEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class TransactionPageController {

    private static final long TIMEOUT = 3600L * 1000; // 1 hour in milliseconds
    private final AtomicReference<String> latestPaymentData = new AtomicReference<>();

    @PostMapping("/transactions")
    public String processPayments(@RequestBody List<PaymentEntity> paymentEntities) {
        System.out.println("Received Payments: " + paymentEntities);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String paymentsJson = objectMapper.writeValueAsString(paymentEntities);
            latestPaymentData.set(paymentsJson);
            return paymentsJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error processing the payments.";
        }
    }

    @GetMapping("/transaction/sse")
    public SseEmitter streamEvents() {
        SseEmitter emitter = new SseEmitter(TIMEOUT);
        AtomicBoolean isCompleted = new AtomicBoolean(false);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        System.out.println("SSE connection started for /sse");

        executor.submit(() -> {
            try {
                while (!isCompleted.get()) {
                    String dataToSend = latestPaymentData.getAndSet(null); // Get and clear data

                    // Send payment data only if it exists (newly arrived)
                    if (dataToSend != null && !dataToSend.isEmpty()) {
                        System.out.println("Sending payment data: " + dataToSend);
                        emitter.send(SseEmitter.event()
                                .data(dataToSend)
                                .name("payment-update"));
                    }

                    // Always send heartbeat
                    System.out.println("Sending heartbeat event...");
                    emitter.send(SseEmitter.event()
                            .data("{\"event\": \"\"}")
                            .name("heartbeat"));

                    Thread.sleep(10000); // 1-second interval
                }
                emitter.complete();
            } catch (IOException e) {
                System.err.println("IOException in SSE thread: " + e.getMessage());
                emitter.completeWithError(e);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
                emitter.complete();
            } catch (Exception e) {
                System.err.println("Unexpected error in SSE thread: " + e.getMessage());
                emitter.completeWithError(e);
            } finally {
                executor.shutdown();
                System.out.println("SSE thread terminated.");
            }
        });

        emitter.onCompletion(() -> {
            System.out.println("SSE connection completed.");
            isCompleted.set(true);
        });

        emitter.onTimeout(() -> {
            System.out.println("SSE stream timed out after " + TIMEOUT + "ms.");
            emitter.complete();
            isCompleted.set(true);
        });

        emitter.onError((throwable) -> {
            System.err.println("SSE error occurred: " + throwable.getMessage());
            isCompleted.set(true);
        });

        return emitter;
    }



//    @GetMapping("/sse")
//    public Flux<String> streamEvents() {
//        // Return a Flux that will emit data periodically
//        return Flux.create(sink -> {
//            // Emit the latest payment data as it becomes available
//            String dataToSend = latestPaymentData.get();
//            if (dataToSend != null) {
//                sink.next(dataToSend);  // Push data
//            } else {
//                sink.next("No payment data available yet.");
//            }
//            sink.complete();  // Mark the stream as complete when no more data will be pushed
//        }).map(data -> data);  // Map any data to String format (could be customized)
//    }
}
