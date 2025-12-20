package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception rzucana gdy żądany trening nie zostanie znaleziony w bazie danych.
 * <p>
 * Dziedziczy po {@link NotFoundException} i jest używana w sytuacjach,
 * gdy operacja wyszukiwania treningu po ID zwróci brak rezultatu.
 * </p>
 * <p>
 * Automatycznie formatuje wiadomość błędu z podanym ID treningu.
 * </p>
 *
 * @author Fitness Tracker Team
 * @see Training
 * @see NotFoundException
 */
@SuppressWarnings("squid:S110")
public class TrainingNotFoundException extends NotFoundException {

    private TrainingNotFoundException(String message) {
        super(message);
    }

    public TrainingNotFoundException(Long id) {
        this("Training with ID=%s was not found".formatted(id));
    }

}
