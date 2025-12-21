package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * klasa mapująca treningi
 */
@Component
public class TrainingMapper {
    /**
     * mapuje Training do TrainingDto
     * @param training
     * @return
     */
    TrainingDto toDto(Training training) {
        User user = training.getUser();

        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );

        return new TrainingDto(
                userDto,
                training.getStartTime(),
                training.getEndTime(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }
}
