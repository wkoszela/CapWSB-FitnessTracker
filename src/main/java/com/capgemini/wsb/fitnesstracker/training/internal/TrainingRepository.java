package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds a list of Training objects by user ID.
     *
     * @param  userId  the ID of the user to search for
     * @return         a list of Training objects associated with the user ID
     */
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                        .filter(training -> Objects.equals(training.getUser().getId(), userId))
                        .toList();
    }

    /**
     * Finds a list of Training objects whose end time is after the given date.
     *
     * @param  date  the date to compare the end time against
     * @return       a list of Training objects with end time after the given date
     */
    default List<Training> findByEndTimeAfter(Date date) {
        return findAll().stream()
                .filter(training -> training.getEndTime().compareTo(date) > 0)
                .toList();
    }

    /**
     * Finds a list of Training objects by activity type.
     *
     * @param  type  the activity type to filter the trainings by
     * @return       a list of Training objects matching the specified activity type
     */
    default List<Training> findByActivityType(ActivityType type) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getActivityType(), type))
                .toList();
    }
}
