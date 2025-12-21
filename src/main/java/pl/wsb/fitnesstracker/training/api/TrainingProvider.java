package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;
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

    /**
     * Pobiera wszystkie treningi.
     *
     * @return lista wszystkich treningów
     */
    List<Training> getAllTrainings();

    /**
     * Pobiera treningi konkretnego użytkownika.
     *
     * @param userId ID użytkownika
     * @return lista treningów użytkownika
     */
    List<Training> getTrainingsByUserId(Long userId);

    /**
     * Pobiera treningi zakończone po podanej dacie.
     *
     * @param afterTime data graniczna
     * @return lista treningów
     */
    List<Training> getFinishedTrainingsAfter(Date afterTime);

    /**
     * Pobiera treningi o określonym typie aktywności.
     *
     * @param activityType typ aktywności
     * @return lista treningów
     */
    List<Training> getTrainingsByActivityType(ActivityType activityType);

}
