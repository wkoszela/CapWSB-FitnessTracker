package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Interface for read-only operations on Training entities.
 */
public interface TrainingProvider {

    /**
     * Retrieves a training by id.
     *
     * @param trainingId id of the training
     * @return Optional containing the training if found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all trainings.
     *
     * @return List of all trainings
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves trainings by user id.
     *
     * @param userId id of the user
     * @return List of trainings
     */
    List<Training> getTrainingsByUserId(Long userId);

    /**
     * Retrieves trainings finished after a specific date.
     *
     * @param afterTime date to compare
     * @return List of trainings
     */
    List<Training> getFinishedTrainingsAfter(Date afterTime);

    /**
     * Retrieves trainings by activity type.
     *
     * @param activityType activity type to filter
     * @return List of trainings
     */
    List<Training> getTrainingsByActivityType(ActivityType activityType);
}
