package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * Mapper for converting between {@link Training} entities and {@link TrainingDto} DTOs.
 */
@Component
class TrainingMapper {

    /**
     * Converts a {@link Training} entity to a {@link TrainingDto}.
     *
     * @param training the training entity
     * @return the training DTO
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                toUserDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    private UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }
}
