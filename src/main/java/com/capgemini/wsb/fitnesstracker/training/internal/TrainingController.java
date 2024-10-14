package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

    @GetMapping
    public ResponseEntity<Object> getTrainings() {
        var trainings = trainingService.findAllTrainings();
        if (trainings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No training found");
        }
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getTrainingByUserId(@PathVariable("userId") Long userId) {
        var trainings = trainingService.getTrainingsByUserId(userId);
        if (trainings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No training found assigned to this user id");
        }
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/finished/{finished}")
    public ResponseEntity<Object> getTrainingsFinishedAfter(@PathVariable("finished") LocalDate date) {
        var trainings = trainingService.findAllTrainings();
        if (trainings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No training found");
        }
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/activityType")
    public ResponseEntity<Object> getUserByEmail(@RequestParam("activityType") String activityType) {
        var trainings = trainingService.findTrainingsOfActivityType(activityType);
        if(trainings.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No trainings with provided activity type found");
        }
        return ResponseEntity.ok(trainings);

    }
}
