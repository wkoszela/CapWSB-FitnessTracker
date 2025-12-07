package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;

import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.UserDto;

@Component
class TrainingMapper {

    TrainingDto toDto(Training training) {
        UserDto userDto = new UserDto(
                training.getUser().getId(),
                training.getUser().getFirstName(),
                training.getUser().getLastName(),
                training.getUser().getBirthdate(),
                training.getUser().getEmail());

        return new TrainingDto(
                training.getId(),
                userDto,
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }
}
