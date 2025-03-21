package pl.wsb.fitnesstracker.training.api;

public interface TrainingService extends TrainingProvider {

    /**
     * This method creates a training using data passed in the provided {@link Training} object.
     *
     * @param training Training to create
     * @param userId User ID to assign the training to
     * @return {@link Training} containing the created entity
     */
    Training createTraining(Training training, Long userId);

    /**
     * This method updates a training using data passed in the provided {@link Training} object.
     *
     * @param training Training to update
     * @param trainingId ID of the training to update
     * @param userId ID of the user to assign the training to
     * @return {@link Training} containing the created entity
     */
    Training updateTraining(Training training, Long trainingId, Long userId);
}
