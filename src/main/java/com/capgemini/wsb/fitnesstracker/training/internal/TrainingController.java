package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    @GetMapping("/getByUser/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.getTrainingsByUser(userId)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    @GetMapping("/endAfter/{date}")
    public List<TrainingDto> getTrainingsEndingAfter(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return trainingService.getTrainingsEndingAfter(date)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    @GetMapping("/byType/{type}")
    public List<TrainingDto> getTrainingsByType(@PathVariable("type") String type) {
        ActivityType activityType = ActivityType.valueOf(type);
        return trainingService.getTrainingsByType(activityType)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingDto trainingDto) {
        Training training = trainingService.createTraining(trainingMapper.toTraining(trainingDto));
        return trainingMapper.toDto(training);
    }
}
