package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.util.Date;

record TrainingDto(@Nullable Long id, UserDto user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
}

record TrainingWithoutUserDto(@Nullable Long id, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {}