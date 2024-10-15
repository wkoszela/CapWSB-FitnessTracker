package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    List<Training> getTrainingsEntityByUserId(Long userId);

    /**
     * Retrieves a {@link List<TrainingDto>} of all trainings.
     *
     * @return A {@link List<TrainingDto>} containing all trainings
     */
    List<TrainingDto> getAllTrainings();

    /**
     * Retrieves a {@link List<TrainingDto>} of all trainings assigned to a user with specified user id
     *
     * @param userId id of the user
     * @return {@link List<TrainingDto>} of trainings assigned to specified user
     */
    List<TrainingDto> getTrainingsByUserId(Long userId);

    /**
     * Retrieves a {@link List<TrainingDto>} of all trainings finished after the specified date
     *
     * @param date date which will be used to compare with endDate field on {@link Training}
     * @return {@link List<TrainingDto>} of trainings with endDate after the specified date
     */
    List<TrainingDto> getTrainingsFinishedAfter(LocalDate date);

    /**
     * Retrieves a {@link List<TrainingDto>} of all trainings with specified {@link ActivityType}
     *
     * @param activityType to be compared with {@link ActivityType} in order to filter trainings
     * @return {@link List<TrainingDto>} of trainings with specified activityType
     */
    List<TrainingDto> getTrainingsOfActivityType(String activityType);

}
