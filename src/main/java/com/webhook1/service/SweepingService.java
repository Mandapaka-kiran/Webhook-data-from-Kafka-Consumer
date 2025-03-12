package com.webhook1.service;

import com.webhook1.dto.SweepingDTO;
import com.webhook1.entity.Sweeping;
import com.webhook1.repository.SweepingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class SweepingService {

    private final SweepingRepository sweepingRepository;

    public SweepingService(SweepingRepository sweepingRepository) {
        this.sweepingRepository = sweepingRepository;
    }

    public ResponseEntity<?> getAllSweepingData(){
        try{
            List<Sweeping> sweepings = sweepingRepository.findAll();
            System.out.println("sweeping data : " + sweepings);

            List<SweepingDTO> sweepingDTOS = sweepings.stream().map(sweeping -> {
                SweepingDTO sweepingDTO = new SweepingDTO();
                sweepingDTO.set_id(sweeping.get_id());
                sweepingDTO.setSweep_name(sweeping.getSweep_name());
                sweepingDTO.setMaster_account(sweeping.getMaster_account());
                sweepingDTO.setSweep_direction(sweeping.getSweep_direction());
                sweepingDTO.setFrequency(sweeping.getFrequency());
                sweepingDTO.setStatus(sweeping.getStatus());
                sweepingDTO.setNext_execution(sweeping.getNext_execution());
                sweepingDTO.setThreshold_limit(sweeping.getThreshold_limit());
                sweepingDTO.setCurrency(sweeping.getCurrency());
                sweepingDTO.setAction(sweeping.getAction());
                return sweepingDTO;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(sweepingDTOS);
        }catch (Exception e){
            String errorMessage = e.getMessage() != null ? e.getMessage() : "Unknown error occurred in get sweeping method";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }

    }
}
