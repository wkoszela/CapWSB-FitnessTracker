package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.User;

/**
 * Maps {@link Training} entities to {@link TrainingDto}s and vice versa.
 *
 * <p>The mapping is intentionally kept simple – all fields are copied
 * directly.  For more complex scenarios (e.g. nested objects or collections)
 * a library such as MapStruct can be used.</p>
 */
@Component
public class TrainingMapper {

    /** Converts an entity to a DTO. */
    public TrainingDto toDto(Training training) {
        TrainingDto.UserDto userDto = new TrainingDto.UserDto(
                training.getUser().getId(),
                training.getUser().getFirstName(),
                training.getUser().getLastName(),
                training.getUser().getEmail()
        );

        return new TrainingDto(
                training.getId(),
                userDto,
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    /** Converts a DTO to an entity.  The {@code user} is supplied by the caller
     * (the controller ensures it exists). */
    public Training toEntity(TrainingDto dto, User user) {
        return new Training(
                user,
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getActivityType(),
                dto.getDistance(),
                dto.getAverageSpeed()
        );
    }
}
