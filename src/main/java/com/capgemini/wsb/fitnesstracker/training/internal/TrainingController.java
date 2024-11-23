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

    @GetMapping("/finished-after")
    public List<TrainingDto> getTrainingsFinishedAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
        return trainingService
                .getTrainingsFinishedAfter(date)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/by-activity/{activityName}")
    public List<TrainingDto> getTrainingsByActivity(@PathVariable String activityName) {
        return trainingService.getByActivityType(ActivityType.valueOf(activityName.toUpperCase()))
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody TrainingDto trainingDto) {
        return trainingMapper.toDto(
                trainingService.createTraining(trainingDto)
        );
    }

    @PutMapping("/{trainingId}")
    public TrainingDto updateTraining(@PathVariable Long trainingId, @RequestBody TrainingDto trainingDto) {
        return trainingMapper.toDto(
                trainingService.updateTraining(trainingId, trainingDto)
        );
    }
}
