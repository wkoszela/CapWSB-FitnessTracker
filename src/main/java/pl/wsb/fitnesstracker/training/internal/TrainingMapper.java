package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

@Component
class TrainingMapper {

    TrainingDto toDto(Training training) {
        TrainingDto dto = new TrainingDto();
        dto.setId(training.getId());
        dto.setStartTime(training.getStartTime());
        dto.setEndTime(training.getEndTime());
        dto.setActivityType(training.getActivityType());
        dto.setDistance(training.getDistance());
        dto.setAverageSpeed(training.getAverageSpeed());

        if (training.getUser() != null) {
            dto.setUser(new UserSimpleDto(
                    training.getUser().getId(),
                    training.getUser().getFirstName(),
                    training.getUser().getLastName()));
        }
        return dto;
    }
}