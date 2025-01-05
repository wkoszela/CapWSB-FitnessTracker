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