package pl.wsb.fitnesstracker.training.api;

import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .toList();
    }
}
