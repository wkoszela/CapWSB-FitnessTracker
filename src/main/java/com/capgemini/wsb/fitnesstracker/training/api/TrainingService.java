package com.capgemini.wsb.fitnesstracker.training.api;

import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link Training} entities through the API.
 */
public interface TrainingService {

    /**
     * Creates a new training.
     *
     * @param training to be created
     * @return The created {@link Training}
     */
    Training createTraining(Training training);

    /**
     * Updates an existing training.
     *
     * @param id       of the training to update
     * @param training updated training data
     * @return Updated {@link Training}
     */
    Training updateTraining(Long id, UpdateTrainingDto training);

    /**
     * Deletes a training by id.
     *
     * @param id of the training to delete
     */
    void deleteTraining(Long id);
}
