package com.webhook1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Data
public class SweepingDTO {
    private String _id;
    private String sweep_name;
    private String master_account;
    private String sweep_direction;
    private String frequency;
    private String status;
    @JsonFormat(pattern = "yyyy-mm-dd'T'HH:mm:ss")
    private LocalDateTime next_execution;
    private double threshold_limit;
    private String currency;
    private String action;
}
