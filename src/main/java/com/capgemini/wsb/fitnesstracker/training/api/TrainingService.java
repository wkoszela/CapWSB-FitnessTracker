package com.capgemini.wsb.fitnesstracker.training.api;


/**
 * Interface (API) for modifying operations on {@link Training} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface TrainingService {

    /**
     * A method to create a new training entity.
     *
     * @param  training  the Training object to be created
     * @return           the created Training object
     */
    Training createTraining(Training training);

    /**
     * Updates a training entity.
     *
     * @param  training  the Training object to be updated
     * @return           the updated Training object
     */
    Training updateTraining(Training training);
}
