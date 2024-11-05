package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TrainingDto {
    private Long id;
    private Long userId;
    private Date startTime;
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;
}
