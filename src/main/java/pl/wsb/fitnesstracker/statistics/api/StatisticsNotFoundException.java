/*
 ____            _            _____ _             _     _      ___ ___ ___ ___ ___
|    \ ___ _____|_|___ ___   |   __| |_ ___ ___  |_|___| |_   | . | . | . |_  |  _|
|  |  | .'|     | | .'|   |  |__   |  _|  _| . | | | -_| '_|  |_  |_  | . |_  | . |
|____/|__,|_|_|_|_|__,|_|_|  |_____|_| |_| |___|_| |___|_,_|  |___|___|___|___|___|
                                               |___|
 */
package pl.wsb.fitnesstracker.statistics.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception thrown when statistics are not found.
 */
public class StatisticsNotFoundException extends NotFoundException {

    /**
     * Creates a new exception with a message.
     *
     * @param message message describing the exception
     */
    private StatisticsNotFoundException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with a message indicating that statistics with given ID was not found.
     *
     * @param id ID of the statistics that was not found
     */
    public StatisticsNotFoundException(Long id) {
        this("Statistics with ID=%s was not found".formatted(id));
    }
}