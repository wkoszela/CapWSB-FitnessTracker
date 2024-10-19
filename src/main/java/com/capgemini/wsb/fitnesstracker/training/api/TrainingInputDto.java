package com.capgemini.wsb.fitnesstracker.training.api;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TrainingInputDto {

    private Long userId;

    private Date startTime;

    private Date endTime;

    private ActivityType activityType;

    private double distance;

    private double averageSpeed;

    public TrainingInputDto(Long userId, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}
