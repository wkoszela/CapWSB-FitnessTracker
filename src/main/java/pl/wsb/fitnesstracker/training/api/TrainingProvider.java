package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training by its ID.
     * If the training with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves trainings by user ID.
     *
     * @param userId id of the user to find trainings for
     * @return A {@link List} containing all found trainings,
     */
    List<Training> findTrainingsByUserId(Long userId);

    /**
     * Retrieves trainings with the given activity type.
     *
     * @param activityType activity type to find trainings by
     * @return A {@link List} containing all found trainings,
     */
    List<Training> findTrainingsByActivityType(ActivityType activityType);

    /**
     * Retrieves trainings finished after the given date.
     *
     * @param date date used to find trainings
     * @return A {@link List} containing all found trainings,
     */
    List<Training> findTrainingsWithEndDateAfter(Date date);

    /**
     * Retrieves all trainings.
     *
     * @return A {@link List} containing the all trainings,
     */
    List<Training> findAllTrainings();
}
