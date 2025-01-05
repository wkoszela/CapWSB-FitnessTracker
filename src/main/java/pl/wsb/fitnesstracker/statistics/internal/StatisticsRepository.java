/*
 ____            _            _____ _             _     _      ___ ___ ___ ___ ___
|    \ ___ _____|_|___ ___   |   __| |_ ___ ___  |_|___| |_   | . | . | . |_  |  _|
|  |  | .'|     | | .'|   |  |__   |  _|  _| . | | | -_| '_|  |_  |_  | . |_  | . |
|____/|__,|_|_|_|_|__,|_|_|  |_____|_| |_| |___|_| |___|_,_|  |___|___|___|___|___|
                                               |___|
 */
package pl.wsb.fitnesstracker.statistics.internal;

import pl.wsb.fitnesstracker.statistics.api.Statistics;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for {@link Statistics}.
 */
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    /**
     * Returns statistics with the given burned calories.
     *
     * @param burnedCalories burned calories
     * @return statistics with the given burned calories
     */
    default List<Statistics> findByMoreBurnedCalories(Long burnedCalories) {
        return findAll().stream()
                .filter(statistics -> statistics.getTotalCaloriesBurned() > burnedCalories)
                .toList();
    }
}