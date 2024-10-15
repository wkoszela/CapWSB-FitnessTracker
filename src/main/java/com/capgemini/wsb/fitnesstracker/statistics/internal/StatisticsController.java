package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsServiceImpl statisticsService;

    @PutMapping
    public ResponseEntity<Object> generateStatistics() {
        try {
            return ResponseEntity.ok(statisticsService.generateStatistics());
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        }
    }
}
