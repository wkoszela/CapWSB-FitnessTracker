package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByUserId(Long userId);

    List<Training> findByEndTimeAfter(Date afterTime);

    List<Training> findByActivityType(ActivityType activityType);
}
