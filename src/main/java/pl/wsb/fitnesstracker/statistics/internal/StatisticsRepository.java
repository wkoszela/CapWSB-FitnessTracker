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