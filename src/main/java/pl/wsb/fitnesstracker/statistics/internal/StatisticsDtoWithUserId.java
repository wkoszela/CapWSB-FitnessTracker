package pl.wsb.fitnesstracker.statistics.internal;

import jakarta.annotation.Nullable;

/**
 * Represents statistics of a user.
 */
public record StatisticsDtoWithUserId(
        @Nullable Long id,
        @Nullable Long userId,
        int totalTrainings,
        double totalDistance,
        int totalCaloriesBurned
){}