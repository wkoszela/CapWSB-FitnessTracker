package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.annotation.Nullable;

public record StatisticsDto(@Nullable Long id, User user, int totalTrainings,
                            double totalDistance, int totalCaloriesBurned) {
}
