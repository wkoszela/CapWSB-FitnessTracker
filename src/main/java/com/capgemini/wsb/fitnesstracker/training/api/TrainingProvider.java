package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
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
     * Deletes all trainings associated with a specific user.
     * <p>
     * This method finds and deletes all training records in the database that are linked to the specified user ID.
     *
     * @param userId the ID of the user whose trainings are to be deleted
     */
    void deleteTrainingByUserId(Long userId);

    /**
     * Retrieves all trainings.
     *
     * @return a list of all {@link Training} objects
     */
    List<Training> findAllTrainings();

    /**
     * Retrieves all trainings for a specific user.
     *
     * @param userId the ID of the user whose trainings are to be retrieved
     * @return a list of {@link Training} objects for the specified user
     */
    List<Training> findTrainingsByUserId(Long userId);

    /**
     * Retrieves all trainings that were completed on the specified date.
     *
     * @param date the date to compare against the end time of trainings
     * @return a list of {@link Training} objects whose end time is on the specified date
     */
    List<Training> findCompletedTrainings(LocalDate date);

    /**
     * Retrieves all trainings of a specific activity type.
     *
     * @param activityType the type of activity to filter by
     * @return a list of {@link Training} objects of the specified activity type
     */
    List<Training> findTrainingsByActivityType(ActivityType activityType);

    /**
     * Creates a new training.
     *
     * @param training the training object to be created
     * @return the created {@link Training} object
     */
    Training createTraining(Training training);

    /**
     * Updates an existing training.
     *
     * @param id          the ID of the training to be updated
     * @param trainingDto the training data transfer object containing the updated details
     * @return the updated {@link Training} object
     */
    Training updateTraining(Long id, TrainingDto trainingDto);

}
