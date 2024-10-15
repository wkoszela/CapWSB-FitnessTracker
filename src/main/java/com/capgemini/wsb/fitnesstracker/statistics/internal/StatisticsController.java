package com.capgemini.wsb.fitnesstracker.statistics.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsServiceImpl statisticsService;

    @GetMapping
    public ResponseEntity<Object> getStatistics() {
        var statistics = statisticsService.getAllStatistics();
        if (statistics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No statistics found");
        }
        return ResponseEntity.ok(statistics);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> generateStatistics(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(statisticsService.generateStatisticsForSpecifiedUser(userId));
    }
}
