package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByUserId(Long userId);

    default List<Training> findByEndTimeAfter(Date afterTime) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(afterTime))
                .collect(Collectors.toList());
    }

    default List<Training> findByActivityType(ActivityType activityType) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getActivityType(), activityType))
                .collect(Collectors.toList());
    }
}
