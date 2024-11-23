package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    /**
     * Finds statistics by the user's ID.
     *
     * @param userId the ID of the user
     * @return a list of statistics
     */
    List<Statistics> findByUserId(Long userId);

    /**
     * Finds statistics where the total calories burned is greater than the specified value.
     *
     * @param minCalories the minimum number of calories
     * @return a list of statistics
     */
    List<Statistics> findByTotalCaloriesBurnedGreaterThan(int minCalories);
}
