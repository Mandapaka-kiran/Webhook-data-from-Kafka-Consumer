package com.webhook1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.webhook1.dto.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.time.LocalDate;

@Service
public class CashFlowForecastService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String FORECAST_URL = "http://127.0.0.1:8000/daily_cashflow";

//    public CashFlowForecastRequest getForecast(CashFlowForecastRequest cashFlowDTO, PaymentEntity paymentEntity){
//        System.out.println("Received Payments: " + paymentEntity);
//        CashFlowForecastResponse cashFlowForecastResponse = new CashFlowForecastResponse();
//        cashFlowForecastResponse.setOpeningBalance(cashFlowDTO.getOpeningBalance());
//        cashFlowForecastResponse.setTotalCashInflow(cashFlowDTO.getTotalCashInflow());
//        cashFlowForecastResponse.setTotalCashOutflow(cashFlowDTO.getTotalCashOutflow());
//        cashFlowForecastResponse.setNetCashFlow(cashFlowDTO.getNetCashFlow());
//        cashFlowForecastResponse.setProjectedClosingBalance(cashFlowDTO.getProjectedClosingBalance());
//
//        cashFlowForecastResponse.setMsgId();
//        cashFlowForecastResponse.setInstrument();
//        cashFlowForecastResponse.setNetwork();
//        cashFlowForecastResponse.setInflow();
//        cashFlowForecastResponse.setOutflow();
//        cashFlowForecastResponse.setStatus();
//        cashFlowForecastResponse.setSettlementDate();
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        try {
//            String paymentsJson = objectMapper.writeValueAsString(paymentEntities);
//            latestPaymentData.set(paymentsJson); // Update with new payment data
//            return paymentsJson;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return "Error processing the payments.";
//        }
//        return cashFlowDTO;
//    }

    public ProjectedForecastDTOResponse getProjectPrediction(){



//        HttpEntity<LocalDate> httpEntity = new HttpEntity<>(date);
//
//        ResponseEntity<Object> response = restTemplate.exchange(
//                FORECAST_URL+date, HttpMethod.POST, httpEntity, Object.class);
//        System.out.println("response : " + response);

        ProjectedForecastDTOResponse projectedForecastDTO = new ProjectedForecastDTOResponse();
        ProjectedForecastDTOResponse.ProjectedData data = new ProjectedForecastDTOResponse.ProjectedData();
        data.setProjectedOpeningBalance(200000000.00);
        data.setProjectedCashOutflow(120000000.00);
        data.setProjectedCashOutflow(220000000.00);
        data.setProjectedNetCashFlow(100000000.00);
        data.setProjectedClosingBalance(100000000.00);
        projectedForecastDTO.setProjectedData(data);
        return projectedForecastDTO;
    }

}
