package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

public record StatisticsDto(
        @Nullable Long id,
        @Nullable Long userId,
        @Nullable UserDto user,
        int totalTrainings,
        double totalDistance,
        int totalCaloriesBurned
) {
}