package pl.wsb.fitnesstracker.statistics.api;

import java.util.Optional;

/**
 * Interface (API) dla operacji czytających dane statystyk treningów.
 * <p>
 * Definiuje publiczny interfejs dostawcy (provider) danych statystyk.
 * Dostarcza metody do pobierania statystyk treningów użytkownika z bazy danych
 * bez możliwości modyfikacji danych.
 * </p>
 *
 * @author Fitness Tracker Team
 * @see Statistics
 */
public interface StatisticsProvider {

    /**
     * Retrieves a statistics based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param statisticsId id of the statistics to be searched
     * @return An {@link Optional} containing the located Statistics, or {@link Optional#empty()} if not found
     */
    Optional<Statistics> getStatistics(Long statisticsId);

}
