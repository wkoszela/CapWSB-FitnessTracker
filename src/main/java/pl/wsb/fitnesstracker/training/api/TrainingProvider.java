package pl.wsb.fitnesstracker.training.api;

import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    List<TrainingDto> findAll();

}
