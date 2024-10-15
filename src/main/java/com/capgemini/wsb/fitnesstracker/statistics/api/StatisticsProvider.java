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

}
