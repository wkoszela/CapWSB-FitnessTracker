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

    /**
     * Retrieves all trainings and maps them to a list of TrainingDto objects.
     *
     * @return  a list of TrainingDto objects representing all trainings
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    /**
     * Retrieves a list of trainings associated with the given user ID.
     *
     * @param  userId  the ID of the user
     * @return         a list of TrainingDto objects representing the trainings associated with the user
     */
    @GetMapping("/getByUser/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.getTrainingsByUser(userId)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    /**
     * Retrieves a list of trainings that end after the specified date.
     *
     * @param  date  the date to compare against the end time of trainings
     * @return       a list of trainings that end after the specified date
     */
    @GetMapping("/endAfter/{date}")
    public List<TrainingDto> getTrainingsEndingAfter(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return trainingService.getTrainingsEndingAfter(date)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    /**
     * Retrieves a list of TrainingDto objects based on the specified activity type.
     *
     * @param  type  the activity type to filter the trainings by
     * @return       a list of TrainingDto objects matching the specified activity type
     */
    @GetMapping("/byType/{type}")
    public List<TrainingDto> getTrainingsByType(@PathVariable("type") String type) {
        ActivityType activityType = ActivityType.valueOf(type);
        return trainingService.getTrainingsByType(activityType)
                              .stream()
                              .map(trainingMapper::toDto)
                              .toList();
    }

    /**
     * Creates a new training entity for a specific user.
     *
     * @param  userId         the ID of the user for whom the training is created
     * @param  trainingDto    the TrainingWithoutUserDto object containing training details
     * @return                the created TrainingDto object
     */
    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@PathVariable Long userId, @RequestBody TrainingWithoutUserDto trainingDto) {
        User user = userService.getUser(userId)
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Training training = trainingService.createTraining(trainingMapper.toTraining(trainingDto, user, null));
        return trainingMapper.toDto(training);
    }

    /**
     * Updates a training entity with the given ID.
     *
     * @param  id          the ID of the training entity to update
     * @param  trainingDto the TrainingWithoutUserDto object containing the updated training details
     * @return             the updated TrainingDto object
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingWithoutUserDto trainingDto) {
        Training originalTraining = trainingService.getById(id)
                                                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Training training = trainingService.updateTraining(trainingMapper.toTraining(trainingDto, originalTraining.getUser(), id));
        return trainingMapper.toDto(training);
    }
}
