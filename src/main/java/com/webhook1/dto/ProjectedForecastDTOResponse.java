package com.webhook1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectedForecastDTOResponse {

    private ProjectedData projectedData;

    @Data
    public static class ProjectedData {

//        private LocalDate date;
//        private double opening_balance;
//        private double projected_inflow;
//        private double projected_outflow;
//        private double projected_net_cash_flow;
//        private double projected_ClosingBalance;

        private double projectedOpeningBalance;
        private double projectedCashInflow;
        private double projectedCashOutflow;
        private double projectedNetCashFlow;
        private double projectedClosingBalance;
    }
}
