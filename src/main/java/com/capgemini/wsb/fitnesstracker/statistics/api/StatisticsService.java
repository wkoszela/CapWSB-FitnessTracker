package com.capgemini.wsb.fitnesstracker.statistics.api;

public interface StatisticsService {

    /**
     * Deletes a statistics based on their ID.
     *
     * @param statisticsId id of the statistics to be deleted
     * @return             {@code true} if deleted successfully, {@code false} otherwise
     */
    boolean deleteStatistics(Long statisticsId);

    /**
     * Creates a new statistics entry.
     *
     * @param statistics statistics to be created
     * @return           the created statistics
     */
    Statistics createStatistics(Statistics statistics);

    /**
     * Updates an existing statistics entry.
     *
     * @param statistics statistics to be updated
     * @return           the updated statistics
     */
    Statistics updateStatistics(Statistics statistics);
}
