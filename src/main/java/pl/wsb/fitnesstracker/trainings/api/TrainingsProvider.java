package pl.wsb.fitnesstracker.trainings.api;

import java.util.Optional;

public interface TrainingsProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Trainings> getTrainings(Long trainingId);

}
