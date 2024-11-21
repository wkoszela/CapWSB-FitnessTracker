package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.util.Date;

public record TrainingDto(
        @Nullable Long id,
        @Nullable Long userId,
        @Nullable UserDto user,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        Date startTime,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {
}
