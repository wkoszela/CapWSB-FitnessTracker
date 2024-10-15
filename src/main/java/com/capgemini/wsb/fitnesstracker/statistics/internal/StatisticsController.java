package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
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
    public ResponseEntity<Object> reGenerateStatistics() {
        try {
            return ResponseEntity.ok(statisticsService.reGenerateStatistics());
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> reGenerateStatisticsForSpecifiedUser(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(statisticsService.reGenerateStatisticsForSpecifiedUser(userId));
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        }
    }
}
