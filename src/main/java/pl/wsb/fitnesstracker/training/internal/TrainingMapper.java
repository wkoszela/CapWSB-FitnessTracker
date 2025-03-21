package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;

@Component
@RequiredArgsConstructor
public class TrainingMapper {

    @Autowired
    private final UserMapper trainingUserMapper;

    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                trainingUserMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    Training toEntity(TrainingShallowDto dto) {
        return new Training(
                null,
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getActivityType(),
                dto.getDistance(),
                dto.getAverageSpeed()
        );
    }
}
