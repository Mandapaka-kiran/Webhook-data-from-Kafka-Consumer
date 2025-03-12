package com.webhook1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PoolingDTO {
    private String _id;
    private String pool_name;
    private String master_account;
    private String currency;
    private Object participating_accounts;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime next_execution;
    private double balance;
    private double liquidity_threshold;
    private String action;
}
