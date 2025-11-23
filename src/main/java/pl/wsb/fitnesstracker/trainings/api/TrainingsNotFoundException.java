package pl.wsb.fitnesstracker.trainings.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link Trainings} was not found.
 */
@SuppressWarnings("squid:S110")
public class TrainingsNotFoundException extends NotFoundException {

    private TrainingsNotFoundException(String message) {
        super(message);
    }

    public TrainingsNotFoundException(Long id) {
        this("Training with ID=%s was not found".formatted(id));
    }

}
