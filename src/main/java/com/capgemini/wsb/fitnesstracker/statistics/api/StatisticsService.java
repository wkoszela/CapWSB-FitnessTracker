package com.capgemini.wsb.fitnesstracker.statistics.api;


import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;

import java.util.List;

/**
 * Interface (API) for modifying operations on {@link Statistics} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface StatisticsService {

    /**
     * Generate statistics for all users based on their assigned trainings
     *
     * @return {@link List<StatisticsDto>} of all user statistics
     */
    List<StatisticsDto> generateStatistics();

    /**
     * Generate statistics for a user with specified id
     *
     * @param userId An id of a user whose statistics to generate
     * @return {@link Statistics} generated for specified user
     * @throws UserNotFoundException When no user with specified id exists
     */
    Statistics generateStatisticsForSpecifiedUser(Long userId) throws UserNotFoundException;
}
