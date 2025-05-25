package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

@Component
@RequiredArgsConstructor
public class TrainingMapper {

    private final UserService userService;

    public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getUser().getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    public Training toEntity(TrainingDto dto) {
        return new Training(
                userService.findById(dto.userId()),
                dto.startTime(),
                dto.endTime(),
                dto.activityType(),
                dto.distance(),
                dto.averageSpeed()
        );
    }


}
