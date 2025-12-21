package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByUserId(Long userId);

    List<Training> findByEndTimeAfter(Date afterTime);

    List<Training> findByActivityType(ActivityType activityType);
}