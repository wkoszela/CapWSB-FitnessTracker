package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.api.CreateTrainingRequest;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * Controller for managing trainings. It provides endpoints for getting all trainings, getting all trainings for dedicated user,
 * getting all finished trainings after specified time, getting all trainings by activity type and creating new training.
 * All endpoints are available under /v1/trainings path.
 */
@RestController
@RequestMapping("/v1/trainings")
@AllArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    @GetMapping
    private List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("{userId}")
    private List<TrainingDto> getAllTrainingsForDedicatedUser(@PathVariable long userId) {
        return trainingService.getTrainingsByUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getAllFinishedTrainingsAfterTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        java.sql.Date sqlAfterTime = new java.sql.Date(afterTime.getTime());
        return trainingService.getAllTrainingsFinishedAfter(sqlAfterTime).stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getAllTrainingByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.getAllTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TrainingDto addUser(@RequestBody CreateTrainingRequest createTrainingRequest) {
        return trainingMapper.toDto(trainingService.createTraining(createTrainingRequest));
    }

    @PutMapping("/{trainingId}")
    public TrainingDto updateTraining(@PathVariable long trainingId, @RequestBody CreateTrainingRequest createTrainingRequest) {
        return trainingMapper.toDto(trainingService.updateTraining(trainingId, createTrainingRequest));
    }

    @DeleteMapping("/{trainingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTraining(@PathVariable long trainingId) {
        trainingService.deleteTraining(trainingId);
    }
}
