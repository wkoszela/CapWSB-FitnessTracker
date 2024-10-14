package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;

/**
 * Interface (API) for modifying operations on {@link Training} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface TrainingService {

    /**
     * Create new training
     * Throws a {@link UserNotFoundException} when trying to create training for non-existent user
     *
     * @param trainingInputDto Dto with user id data to be mapped onto training entity and saved to db
     * @return {@link TrainingDto} of created training
     */
    TrainingDto createTraining(TrainingInputDto trainingInputDto) throws UserNotFoundException;

    /**
     * Updates training of specified {@param id} with the data in {@param trainingInputDto}
     * Throws a {@link UserNotFoundException} when trying to update training for non-existent user
     * Throws a {@link TrainingNotFoundException} when trying to update non-existent training
     *
     * @param id id of training to be updated
     * @param trainingInputDto data used to replace current training with
     * @return {@link TrainingDto} of updated training
     */
    TrainingDto updateTraining(Long id, TrainingInputDto trainingInputDto) throws UserNotFoundException, TrainingNotFoundException;
}
