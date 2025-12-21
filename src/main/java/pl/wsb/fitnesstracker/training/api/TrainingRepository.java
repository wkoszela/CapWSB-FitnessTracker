package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;

/**
 * Repozytorium dla encji {@link Training}.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Znajduje wszystkie treningi przypisane do danego użytkownika.
     *
     * @param userId ID użytkownika
     * @return lista treningów użytkownika
     */
    List<Training> findByUserId(Long userId);

    /**
     * Znajduje wszystkie treningi zakończone po określonej dacie.
     *
     * @param afterTime data graniczna
     * @return lista treningów zakończonych po podanej dacie
     */
    List<Training> findByEndTimeAfter(Date afterTime);

    /**
     * Znajduje wszystkie treningi o określonym typie aktywności.
     *
     * @param activityType typ aktywności
     * @return lista treningów o danym typie
     */
    List<Training> findByActivityType(ActivityType activityType);
}