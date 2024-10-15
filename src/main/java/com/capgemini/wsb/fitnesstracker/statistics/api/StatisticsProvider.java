package com.capgemini.wsb.fitnesstracker.statistics.api;

import java.util.List;
import java.util.Optional;

public interface StatisticsProvider {

    /**
     * Retrieves all statistics
     *
     * @return {@link List<StatisticsDto>} of all statistics
     */
    List<StatisticsDto> getAllStatistics();

    /**
     * Retrieves statistics for specified user
     *
     * @param userId An id of a user whose statistics to get
     * @return {@link Optional} of statistics. Returns {@link Optional#empty} when no statistics found
     */
    Optional<StatisticsDto> getStatisticsForSpecifiedUser(Long userId);

}
