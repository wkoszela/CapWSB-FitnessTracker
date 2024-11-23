package com.capgemini.wsb.fitnesstracker.statistics.api;

import jakarta.annotation.Nullable;

public record StatisticsUpdateDto(
        @Nullable Long userId,
        int totalTrainings,
        double totalDistance,
        int totalCaloriesBurned
) {
}
