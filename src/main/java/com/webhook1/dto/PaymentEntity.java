package com.webhook1.dto;

import lombok.Data;


@Data
public class PaymentEntity {

    private long id;
    private String msgId;
    private String creditorName;
    private String debtorName;
    private String creditorNetworkType;
    private String debtorNetworkType;
    private String clearingNetwork;
    private String tier;
    private String status;
    private double amount;
    private String cashFlow;
    private String creationDateTime;

}


