package pl.wsb.fitnesstracker.statistics.api;

/**
 * Service interface for statistics.
 */
public interface StatisticsService {

    /**
     * Creates a new statistics record.
     *
     * @param statistics the statistics to create
     * @return the created statistics
     */
    Statistics createStatistics(Statistics statistics);

    /**
     * Updates an existing statistics record.
     *
     * @param statistics the statistics to update
     * @return the updated statistics
     */
    Statistics updateStatistics(Statistics statistics);

    /**
     * Deletes an existing statistics record.
     *
     * @param id the id of the statistics to delete
     */
    void deleteStatistics(Long id);

}