package pl.wsb.fitnesstracker.training.api;

import java.util.Optional;

/**
 * Interfejs (API) dla operacji pobierania danych treningów.
 */
public interface TrainingProvider {

    /**
     * Pobiera trening na podstawie jego ID.
     * Jeśli trening o podanym ID nie zostanie znaleziony, zwraca
     * {@link Optional#empty()}.
     *
     * @param trainingId ID szukanego treningu
     * @return {@link Optional} zawierający trening lub pusty, jeśli nie znaleziono
     */
    Optional<Training> getTraining(Long trainingId);

}
