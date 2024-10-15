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

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getStatisticsForSpecifiedUser(@PathVariable Long userId) {
        var statistics = statisticsService.getStatisticsForSpecifiedUser(userId);

        if (statistics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("No statistics found for specified user");
        }
        return ResponseEntity.ok().body(statistics);

    }

    @GetMapping("/caloriesGreaterThan/{caloriesFloor}")
    public ResponseEntity<Object> getStatisticsForCaloriesGreaterThan(@PathVariable double caloriesFloor) {
        var statistics = statisticsService.getStatisticsWithCaloriesGreaterThen(caloriesFloor);

        if (statistics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("No statistics found with calories greater than " + caloriesFloor);
        }
        return ResponseEntity.ok().body(statistics);

    }

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

    @DeleteMapping
    public ResponseEntity<Object> deleteStatistics() {
        try {
            statisticsService.deleteStatistics();
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Successfully deleted statistics");
        } catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body(e.getMessage());
        }
    }
}
