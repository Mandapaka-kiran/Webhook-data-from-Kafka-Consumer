package com.webhook1.service;

import com.webhook1.dto.PoolingDTO;
import com.webhook1.dto.SweepingDTO;
import com.webhook1.entity.Pooling;
import com.webhook1.entity.Sweeping;
import com.webhook1.repository.PoolingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoolingService {

    private final PoolingRepository poolingRepository;

    public PoolingService(PoolingRepository poolingRepository) {
        this.poolingRepository = poolingRepository;
    }

    public ResponseEntity<?> getAllSweepingData(){
        try{
            List<Pooling> pooling = poolingRepository.findAll();
            System.out.println("pooling data: " + pooling);

            List<PoolingDTO> poolingDTOS = pooling.stream().map(pooling1 -> {
                PoolingDTO poolingDTO = new PoolingDTO();
                poolingDTO.set_id(pooling1.get_id());
                poolingDTO.setPool_name(pooling1.getPool_name());
                poolingDTO.setMaster_account(pooling1.getMaster_account());
                poolingDTO.setCurrency(pooling1.getCurrency());
                poolingDTO.setParticipating_accounts(pooling1.getParticipating_accounts());
                poolingDTO.setStatus(pooling1.getStatus());
                poolingDTO.setNext_execution(pooling1.getNext_execution());
                poolingDTO.setBalance(pooling1.getBalance());
                poolingDTO.setLiquidity_threshold(pooling1.getLiquidity_threshold());
                poolingDTO.setAction(pooling1.getAction());
                return poolingDTO;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(poolingDTOS);
        }catch (Exception e){
            String errorMessage = e.getMessage() != null ? e.getMessage() : "Unknown error occurred in get pooling method";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

    }
}
