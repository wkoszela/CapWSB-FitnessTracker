package pl.wsb.fitnesstracker.statistics.api;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository dla encji Statistics.
 * <p>
 * Dostarcza metody dostępu do danych statystyk treningów w bazie danych.
 * Dziedziczy standardowe operacje CRUD z JpaRepository.
 * </p>
 *
 * @author Fitness Tracker Team
 */
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
