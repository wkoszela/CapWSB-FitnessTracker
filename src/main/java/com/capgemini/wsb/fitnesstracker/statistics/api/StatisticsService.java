package com.capgemini.wsb.fitnesstracker.statistics.api;

/**
 * Interface (API) for modifying operations on {@link Statistics} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface StatisticsService {

    /**
     * Creates new statistics.
     *
     * @param statistics statistics to be created
     * @return The created {@link Statistics}
     */
    Statistics createStatistics(Statistics statistics);

    /**
     * Updates existing statistics.
     *
     * @param id               ID of the statistics to update
     * @param statisticsUpdateDto DTO containing updated statistics data
     * @return The updated {@link Statistics}, or {@code null} if not found
     */
    Statistics updateStatistics(Long id, StatisticsUpdateDto statisticsUpdateDto);

    /**
     * Deletes statistics by ID.
     *
     * @param id ID of the statistics to delete
     */
    void deleteStatistics(Long id);
}
