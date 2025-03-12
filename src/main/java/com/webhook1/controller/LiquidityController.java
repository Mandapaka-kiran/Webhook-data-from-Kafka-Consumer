package com.webhook1.controller;

import com.webhook1.service.PoolingService;
import com.webhook1.service.SweepingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiquidityController {
    private final PoolingService poolingService;
    private final SweepingService sweepingService;

    public LiquidityController(PoolingService poolingService, SweepingService sweepingService) {
        this.poolingService = poolingService;
        this.sweepingService = sweepingService;
    }

    @GetMapping("/pooling_data")
    public ResponseEntity<?> getAllPooling(){
        return poolingService.getAllSweepingData();
    }

    @GetMapping("/sweeping_data")
    public ResponseEntity<?> getAllSweeping(){
        return sweepingService.getAllSweepingData();
    }
}
