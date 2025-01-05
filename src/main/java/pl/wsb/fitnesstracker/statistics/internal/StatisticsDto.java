/*
 ____            _            _____ _             _     _      ___ ___ ___ ___ ___
|    \ ___ _____|_|___ ___   |   __| |_ ___ ___  |_|___| |_   | . | . | . |_  |  _|
|  |  | .'|     | | .'|   |  |__   |  _|  _| . | | | -_| '_|  |_  |_  | . |_  | . |
|____/|__,|_|_|_|_|__,|_|_|  |_____|_| |_| |___|_| |___|_,_|  |___|___|___|___|___|
                                               |___|
 */
package pl.wsb.fitnesstracker.statistics.internal;

import pl.wsb.fitnesstracker.user.api.UserDto;

import jakarta.annotation.Nullable;

/**
 * Represents statistics of a user.
 */
public record StatisticsDto(
        @Nullable Long id,
        UserDto user,
        int totalTrainings,
        double totalDistance,
        int totalCaloriesBurned
){}