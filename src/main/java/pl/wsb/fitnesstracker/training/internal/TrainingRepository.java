package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds all available trainings
     *
     * @return list of all trainings
     */
    default List<Training> findAllTrainings() {
        return findAll();
    }

    /**
     * Finds all trainings for specific user
     *
     * @param userId user identifier
     * @return list of user trainings
     */
    default List<Training> findTrainingsByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    /**
     * Finds all completed trainings after given date
     *
     * @param date completion date
     * @return list of completed trainings
     */
    default List<Training> findCompletedTrainingsAfterDate(Date date) {
        return findAll().stream()
                .filter(training -> training.getEndTime() != null && training.getEndTime().after(date))
                .collect(Collectors.toList());
    }

    /**
     * Finds all trainings by activity type
     *
     * @param activityType type of activity
     * @return list of trainings with specified activity
     */
    default List<Training> findTrainingsByActivityType(String activityType) {
        return findAll().stream()
                .filter(training -> training.getActivityType().name().equalsIgnoreCase(activityType))
                .collect(Collectors.toList());
    }

    /**
     * Creates new training
     *
     * @param training training to create
     * @return created training
     */
    default Training createTraining(Training training) {
        return save(training);
    }

    /**
     * Updates existing training
     *
     * @param training training to update
     * @return updated training
     */
    default Training updateTraining(Training training) {
        return save(training);
    }


}
