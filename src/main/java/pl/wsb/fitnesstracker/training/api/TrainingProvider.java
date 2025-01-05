package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTraining(Long trainingId);

    List<Training> getAllTrainings();

    List<Training> getTrainingsByUserId(Long userId);

    List<Training> getCompletedTrainingsAfter(LocalDate date);

    List<Training> getTrainingsByActivityType(ActivityType activityType);
    
}
