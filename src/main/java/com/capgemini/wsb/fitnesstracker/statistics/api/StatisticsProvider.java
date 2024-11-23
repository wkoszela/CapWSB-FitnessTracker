package com.capgemini.wsb.fitnesstracker.statistics.api;

import java.util.List;
import java.util.Optional;

/**
 * Interface for read-only operations on {@link Statistics} entities.
 */
public interface StatisticsProvider {

    /**
     * Retrieves statistics by ID.
     *
     * @param id ID of the statistics
     * @return An {@link Optional} containing the statistics if found, or {@link Optional#empty()} if not found
     */
    Optional<Statistics> getStatistics(Long id);

    /**
     * Retrieves statistics for a specific user by user ID.
     *
     * @param userId ID of the user
     * @return A list of statistics for the user
     */
    List<Statistics> getStatisticsByUserId(Long userId);

    /**
     * Retrieves all statistics where the total calories burned is greater than the specified value.
     *
     * @param minCalories the minimum number of calories
     * @return A list of statistics
     */
    List<Statistics> getStatisticsByMinCalories(int minCalories);
}
