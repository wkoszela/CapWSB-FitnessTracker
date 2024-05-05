package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;
    private final UserServiceImpl userService;

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


    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@PathVariable Long userId, @RequestBody TrainingWithoutUserDto trainingDto) {
        User user = userService.getUser(userId)
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Training training = trainingService.createTraining(trainingMapper.toTraining(trainingDto, user, null));
        return trainingMapper.toDto(training);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingWithoutUserDto trainingDto) {
        Training originalTraining = trainingService.getById(id)
                                                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Training training = trainingService.updateTraining(trainingMapper.toTraining(trainingDto, originalTraining.getUser(), id));
        return trainingMapper.toDto(training);
    }
}
