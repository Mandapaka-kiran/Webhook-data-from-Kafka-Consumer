package com.webhook1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.webhook1.dto.*;
import org.springframework.stereotype.Service;

@Service
public class CashFlowForecastService {

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
