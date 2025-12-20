package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

/**
 * JPA Repository dla encji Training.
 *
 * Dostarcza metody dostępu do danych treningów w bazie danych.
 * Oprócz standardowych metod z JpaRepository, definiuje custom metody.
 *
 * @author Fitness Tracker Team
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Wyszukuje wszystkie treningi dla danego użytkownika.
     *
     * Implementacja wykorzystuje Stream API do filtrowania,
     * zgodnie z wytycznymi (patrz UserRepository).
     *
     * @param userId ID użytkownika
     * @return Lista treningów użytkownika
     */
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> Objects.equals(
                    training.getUser().getId(), userId))
                .toList();
    }
}
