package pl.wsb.fitnesstracker.training.internal;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

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