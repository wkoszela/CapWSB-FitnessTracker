package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByUserId(Long id);

    List<Training> findByEndTimeAfter(LocalDateTime endTime);

    List<Training> findByActivityType(ActivityType activityType);

}
