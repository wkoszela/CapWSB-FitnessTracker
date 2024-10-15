package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatisticsDto {

    @Nullable
    private Long id;

    private User user;

    private int totalTrainings;

    private double totalDistance;

    private int totalCaloriesBurned;

    public StatisticsDto(Long id, User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.id = id;
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}
