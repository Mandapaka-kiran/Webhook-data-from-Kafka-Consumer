package com.webhook1.dto;

import lombok.Data;

@Data
public class ProjectedForecastDTOResponse {

    private ProjectedData projectedData;

    @Data
    public static class ProjectedData {

        private double projectedOpeningBalance;
        private double projectedCashInflow;
        private double projectedCashOutflow;
        private double projectedNetCashFlow;
        private double projectedClosingBalance;
    }
}
