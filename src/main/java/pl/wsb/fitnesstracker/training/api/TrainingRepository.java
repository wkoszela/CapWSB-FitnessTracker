package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    /** Metoda do e-maila: pobiera wszystkie treningi użytkownika */
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .toList();
    }

    /** Metoda do konsoli: pobiera treningi od konkretnej daty */
    default List<Training> findByUserIdAndStartTimeAfter(Long userId, Date startTime) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .filter(training -> training.getStartTime() != null && training.getStartTime().after(startTime))
                .toList();
    }
}