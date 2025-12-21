package pl.wsb.fitnesstracker.training.api;

import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

/**
 * klasa DTO przechowujaca dane treningu
 */
public class TrainingDto{
    private UserDto user;
    private Date startTime;
    private Date endTime;
    private double distance;
    private double averageSpeed;

    public TrainingDto(UserDto user, Date startTime, Date endTime, double distance, double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public double getDistance() {
        return distance;
    }

    public UserDto getUser() {
        return user;
    }
}
