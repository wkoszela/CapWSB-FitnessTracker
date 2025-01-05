/*
 ____            _            _____ _             _     _      ___ ___ ___ ___ ___
|    \ ___ _____|_|___ ___   |   __| |_ ___ ___  |_|___| |_   | . | . | . |_  |  _|
|  |  | .'|     | | .'|   |  |__   |  _|  _| . | | | -_| '_|  |_  |_  | . |_  | . |
|____/|__,|_|_|_|_|__,|_|_|  |_____|_| |_| |___|_| |___|_,_|  |___|___|___|___|___|
                                               |___|
 */
package pl.wsb.fitnesstracker.training.internal;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TrainingUpdateDto {
    private final Long userId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String activityType;
    private final double distance;
    private final double averageSpeed;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public double getDistance() {
        return distance;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public TrainingUpdateDto(Long userId, LocalDateTime startTime, LocalDateTime endTime, String activityType, double distance, double averageSpeed) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}