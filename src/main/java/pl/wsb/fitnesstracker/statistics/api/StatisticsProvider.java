package pl.wsb.fitnesstracker.statistics.api;

import java.util.Optional;

/**
 * Interfejs (API) dla operacji pobierania danych statystyk.
 */
public interface StatisticsProvider {

    /**
     * Pobiera statystyki na podstawie ich ID.
     * Jeśli statystyki o podanym ID nie zostaną znalezione, zwraca
     * {@link Optional#empty()}.
     *
     * @param statisticsId ID szukanych statystyk
     * @return {@link Optional} zawierający statystyki lub pusty, jeśli nie
     *         znaleziono
     */
    Optional<Statistics> getStatistics(Long statisticsId);

}
