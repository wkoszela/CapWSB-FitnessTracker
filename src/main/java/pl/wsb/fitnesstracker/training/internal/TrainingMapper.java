package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TrainingMapper {

    TrainingDto toTrainingDto(Training training) {
        TrainingUserDto userDto = new TrainingUserDto(
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
                training.getDistance(),
                training.getAverageSpeed(),
                training.getActivityType()
        );
    }

}
