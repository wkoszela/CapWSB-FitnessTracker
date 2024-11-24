package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<Training> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsForUser(@PathVariable Long userId) {

        return trainingService
            .getTrainingsForUser(userId)
            .stream()
            .map(trainingMapper::toDto)
            .toList();
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getTrainingsFinishedAfter(
        @PathVariable
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        Date afterTime
    ) {
        return trainingService
            .getTrainingsFinishedAfter(afterTime)
            .stream()
            .map(trainingMapper::toDto)
            .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivity(@RequestParam String activityType) {
        return trainingService
            .getByActivityType(ActivityType.valueOf(activityType.toUpperCase()))
            .stream()
            .map(trainingMapper::toDto)
            .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody TrainingDataDto createTrainingDto) {
        return trainingMapper.toDto(
            trainingService.createTraining(createTrainingDto)
        );
    }

    @PutMapping("/{trainingId}")
    public TrainingDto updateTraining(@PathVariable Long trainingId, @RequestBody TrainingDataDto trainingDataDto) {
        return trainingMapper.toDto(
            trainingService.updateTraining(trainingId, trainingDataDto)
        );
    }
}
