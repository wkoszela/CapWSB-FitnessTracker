package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTraining(Long trainingId);

    /**
     * Retrieves all trainings.
     *
     * @return A list of all trainings
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves a list of trainings associated with the given user ID.
     *
     * @param  userId  the ID of the user
     * @return         a list of trainings associated with the user
     */
    List<Training> getTrainingsByUser(Long userId);

    /**
     * Retrieves a list of trainings that end after the specified date.
     *
     * @param  date  the date to compare against the end time of trainings
     * @return       a list of trainings that end after the specified date
     */
    List<Training> getTrainingsEndingAfter(Date date);

    /**
     * Retrieves a list of trainings based on the specified activity type.
     *
     * @param  type  the activity type to filter the trainings by
     * @return       a list of trainings matching the specified activity type
     */
    List<Training> getTrainingsByType(ActivityType type);

    /**
     * Retrieves a training based on their ID.
     *
     * @param  id  the ID of the training to be searched
     * @return     An Optional containing the located Training, or Optional.empty() if not found
     */
    Optional<Training> getById(Long id);
}
