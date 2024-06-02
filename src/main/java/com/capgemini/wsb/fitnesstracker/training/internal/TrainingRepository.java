package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import java.util.stream.Collectors;
import java.util.*;
/**
 * Repozytorium JPA dla encji Training, zadaje operacje dostępu do danych z treningów użytkowników.
 */
interface TrainingRepository extends JpaRepository<Training, Long> {
    default List<Training> getFinishedAfterX(Date date)
    {
        return findAll().stream().filter(
                training -> Objects.compare(training.getEndTime(), date, Comparator.naturalOrder()) > 0
        ).collect(Collectors.toList());
    }

    default List<Training> getTrainingsByType(ActivityType type)
    {
        return findAll().stream().filter(
                training -> Objects.equals(training.getActivityType(), type)
        ).collect(Collectors.toList());
    }

    default Training updateTraining(Training training)
    {
        return save(training);
    }

    default List<Training> getTrainingsByUserId(User user) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser(), user))
                .collect(Collectors.toList());
    }
}