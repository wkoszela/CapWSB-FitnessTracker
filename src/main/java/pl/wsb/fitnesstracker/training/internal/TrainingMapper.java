package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * Mapper responsible for converting {@link Training} entities to {@link TrainingDto}.
 */
@Component
class TrainingMapper {

    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                toUserDto(training),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    private UserDto toUserDto(Training training) {
        if (training.getUser() == null) {
            return null;
        }
        return new UserDto(
                training.getUser().getId(),
                training.getUser().getFirstName(),
                training.getUser().getLastName(),
                training.getUser().getBirthdate(),
                training.getUser().getEmail());
    }
}
