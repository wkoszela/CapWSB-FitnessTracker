package pl.wsb.fitnesstracker.training.api;

import java.util.Optional;

/**
 * Interface (API) dla operacji czytających dane treningów.
 * <p>
 * Definiuje publiczny interfejs dostawcy (provider) danych treningów.
 * Dostarcza metody do pobierania treningów z bazy danych
 * bez możliwości modyfikacji danych.
 * </p>
 *
 * @author Fitness Tracker Team
 * @see Training
 */
public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

}
