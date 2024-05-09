package com.capgemini.wsb.fitnesstracker.statistics.api;

import java.util.List;
import java.util.Optional;

public interface StatisticsProvider {

    /**
     * Retrieves a statistics based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param statisticsId id of the statistics to be searched
     * @return An {@link Optional} containing the located Statistics, or {@link Optional#empty()} if not found
     */
    Optional<Statistics> getStatistics(Long statisticsId);

    /**
     * Retrieves all statistics.
     *
     * @return  a list of Statistics objects representing all statistics
     */
    List<Statistics> getAllStatistics();

    /**
     * Retrieves a list of statistics associated with the given user ID.
     *
     * @param  userId  the ID of the user
     * @return         a list of statistics associated with the user
     */
    List<Statistics> getStatisticsByUserId(Long userId);

    /**
     * Retrieves a list of statistics associated with the given user email.
     *
     * @param  email  the email address to search for in user emails
     * @return        a list of Statistics objects representing the user's statistics
     */
    List<Statistics> getStatisticsByUserEmail(String email);

    /**
     * Retrieves a list of statistics with an user age greater than or equal to the specified minimum age.
     *
     * @param  minAge  the minimum age of the users to search for
     * @return         a list of statistics with an user age greater than or equal to the specified minimum age
     */
    List<Statistics> getStatisticsByMinAge(int minAge);
}
