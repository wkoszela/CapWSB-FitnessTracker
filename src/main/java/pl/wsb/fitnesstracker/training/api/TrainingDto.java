package pl.wsb.fitnesstracker.training.api;

import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

/**
 * klasa DTO przechowujaca dane treningu
 * @param user
 * @param startTime
 * @param endTime
 * @param distance
 * @param averageSpeed
 */
public record TrainingDto(UserDto user, Date startTime, Date endTime, double distance, double averageSpeed) {
}
