package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByUserId(Long userId);
    List<Training> findByActivityType(ActivityType activityType);

    default List<Training> findByEndDateAfter(Date date) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(date))
                .toList();
    }
}
