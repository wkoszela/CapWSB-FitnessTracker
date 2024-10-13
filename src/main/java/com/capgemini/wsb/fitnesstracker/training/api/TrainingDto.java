package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class TrainingDto {

    @Getter @Setter private Long id;

    @Getter @Setter private User user;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Getter @Setter private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Getter @Setter private Date endTime;

    @Getter @Setter private ActivityType activityType;

    @Getter @Setter private double distance;

    @Getter @Setter private double averageSpeed;

    public TrainingDto(Long id, User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}
