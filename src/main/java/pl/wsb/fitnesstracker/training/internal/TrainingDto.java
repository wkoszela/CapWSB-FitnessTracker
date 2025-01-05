/*
 ____            _            _____ _             _     _      ___ ___ ___ ___ ___
|    \ ___ _____|_|___ ___   |   __| |_ ___ ___  |_|___| |_   | . | . | . |_  |  _|
|  |  | .'|     | | .'|   |  |__   |  _|  _| . | | | -_| '_|  |_  |_  | . |_  | . |
|____/|__,|_|_|_|_|__,|_|_|  |_____|_| |_| |___|_| |___|_,_|  |___|___|___|___|___|
                                               |___|
 */
package pl.wsb.fitnesstracker.training.internal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public class TrainingDto {
    public double getAverageSpeed() {
        return averageSpeed;
    }

    public Long getId() {
        return id;
    }

    public TrainingUserDto getUser() {
        return user;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public double getDistance() {
        return distance;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    private final Long id;
    private final TrainingUserDto user;
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime startTime;
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime endTime;
    private final double distance;
    private final double averageSpeed;
    private final ActivityType activityType;

    public TrainingDto(Long id, TrainingUserDto user, LocalDateTime startTime, LocalDateTime endTime, double distance, double averageSpeed, ActivityType activityType) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
        this.activityType = activityType;
    }
}