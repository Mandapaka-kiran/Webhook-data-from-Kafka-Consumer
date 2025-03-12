package com.webhook1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CashFlowForecastResponse {

//    private double openingBalance;
//    private double totalCashInflow;
//    private double totalCashOutflow;
//    private double netCashFlow;
//    private double projectedClosingBalance;

    private long id;
    private String msgId;
    private String instrument;
    private String network;
    private String inflow;
    private String outflow;
    private String status;
    private LocalDateTime settlementDate;

//
//    id: "MSG123",
//    instrument: "Cash Receipts from customer",
//    network: "ACH",
//    inflow: "$10,000",
//    outflow: "$0",
//    status: "Completed",
//    settlementDate: "03/03/2025 11:35:27",
}
