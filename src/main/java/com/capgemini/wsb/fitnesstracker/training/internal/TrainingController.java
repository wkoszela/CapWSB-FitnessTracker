package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    @GetMapping
    public ResponseEntity<Object> getTrainings() {
        return ResponseEntity.ok(trainingService.findAllTrainings());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getTrainingByUserId(@PathVariable("userId") Long userId) {
        var trainings = trainingService.getTrainingsByUserId(userId);
        if (trainings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No training found assigned to this user id");
        }
        return ResponseEntity.ok(trainingService.getTrainingsByUserId(userId));
    }

    @GetMapping("/finished/{finished}")
    public ResponseEntity<Object> getTrainingsFinishedAfter(@PathVariable("finished") LocalDate date) {
        return ResponseEntity.ok(trainingService.findTrainingsFinishedAfter(date));
    }
}
