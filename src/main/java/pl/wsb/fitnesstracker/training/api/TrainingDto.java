package pl.wsb.fitnesstracker.training.api;

import lombok.Data;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

import java.util.Date;

@Data
public class TrainingDto {
    private Long id;
    private UserSimpleDto user;
    private Date startTime;
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;
}