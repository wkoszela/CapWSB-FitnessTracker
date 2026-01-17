package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date; // Pamiętaj o imporcie!
import java.util.List;
import java.util.Objects;

/**
 * JPA Repository dla encji Training.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Wyszukuje wszystkie treningi dla danego użytkownika.
     */
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> Objects.equals(
                        training.getUser().getId(), userId))
                .toList();
    }

    /**
     * Metoda potrzebna dla raportu tygodniowego.
     * Filtruje po ID użytkownika i sprawdza czy trening zaczął się po dacie startTime.
     */
    default List<Training> findByUserIdAndStartTimeAfter(Long userId, Date startTime) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getUser().getId(), userId))
                .filter(training -> training.getStartTime() != null && training.getStartTime().after(startTime))
                .toList();
    }
}