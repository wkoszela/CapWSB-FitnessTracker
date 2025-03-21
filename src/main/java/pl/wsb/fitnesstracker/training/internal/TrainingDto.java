package pl.wsb.fitnesstracker.training.internal;

import java.util.Date;

class TrainingDto {

    private final Long id;

    private final UserDto user;

    private final Date startTime;

    private final Date endTime;

    private final ActivityType activityType;

    private final double distance;

    private final double averageSpeed;

    TrainingDto(Long id, UserDto user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    public Long getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
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
}