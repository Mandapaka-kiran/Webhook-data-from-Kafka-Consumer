package com.webhook1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.webhook1.dto.CashFlowForecastResponse;
import com.webhook1.dto.ProjectedForecastDTOResponse;
import com.webhook1.service.CashFlowForecastService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class CashFlowForecastController {

    private final CashFlowForecastService cashFlowForecastService;

    private static final long TIMEOUT = 3600L * 1000; // 1 hour in milliseconds
    private final AtomicReference<String> latestPaymentData = new AtomicReference<>(); // Holds new data


    public CashFlowForecastController(CashFlowForecastService cashFlowForecastService) {
        this.cashFlowForecastService = cashFlowForecastService;
    }

    @GetMapping("/projected_data")
    public ProjectedForecastDTOResponse getPrediction(){
        return cashFlowForecastService.getProjectPrediction();
    }

    @PostMapping("/daily_cashFlow")
    public String processPayments(@RequestBody List<CashFlowForecastResponse> cashFlowForecastResponse) {
        System.out.println("Received Payments: " + cashFlowForecastResponse);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            String paymentsJson = objectMapper.writeValueAsString(cashFlowForecastResponse);
            latestPaymentData.set(paymentsJson);
            return paymentsJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error processing the payments.";
        }
    }

    @GetMapping("/flow_chart/sse")
    public SseEmitter streamEvents() {
        SseEmitter emitter = new SseEmitter(TIMEOUT);
        AtomicBoolean isCompleted = new AtomicBoolean(false);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        System.out.println("SSE connection started for /sse");

        executor.submit(() -> {
            try {
                while (!isCompleted.get()) {
                    String dataToSend = latestPaymentData.getAndSet(null);

                    // Send payment data only if it exists (newly arrived)
                    if (dataToSend != null && !dataToSend.isEmpty()) {
                        System.out.println("Sending transaction data: " + dataToSend);
                        emitter.send(SseEmitter.event()
                                .data(dataToSend)
                                .name("transaction-update"));
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


}
