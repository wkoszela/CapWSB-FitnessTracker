package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Wyjątek informujący o tym, że {@link Training} nie został znaleziony.
 */
@SuppressWarnings("squid:S110")
public class TrainingNotFoundException extends NotFoundException {

    private TrainingNotFoundException(String message) {
        super(message);
    }

    /**
     * Konstruktor wyjątku przyjmujący ID treningu.
     *
     * @param id identyfikator treningu
     */
    public TrainingNotFoundException(Long id) {
        this("Trening o ID=%s nie został znaleziony".formatted(id));
    }

}
