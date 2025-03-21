package pl.wsb.fitnesstracker.training.internal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

class TrainingShallowDto {

    private final Long id;

    private final Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final Date endTime;

    private final ActivityType activityType;

    private final double distance;

    private final double averageSpeed;

    TrainingShallowDto(Long id, Long userId, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this.id = id;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    public Long getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public double getDistance() {
        return distance;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public Long getUserId() {
        return userId;
    }
}