package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainingMapper {

    private final TrainingUserMapper trainingUserMapper;

    TrainingDto toDto(Training training) {
        if (training == null) {
            return null;
        }

        if (training.getUser() == null) {
            throw new InvalidTrainingDataException("Malformed training data, user is not associated to it");
        }

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

    Training toEntity(TrainingDto dto, User user) {
        if (dto == null) {
            return null;
        }

        return new Training(
            user,
            dto.startTime(),
            dto.endTime(),
            dto.activityType(),
            dto.distance(),
            dto.averageSpeed()
        );
    }

    Training toEntity(TrainingDataDto dto, User user) {
        if (dto == null) {
            return null;
        }

        return new Training(
            user,
            dto.startTime(),
            dto.endTime(),
            dto.activityType(),
            dto.distance(),
            dto.averageSpeed()
        );
    }

    Training toUpdateEntity(TrainingDataDto trainingDataDto, Training training, User user) {
        if (trainingDataDto == null || training == null) {
            return training;
        }

        if (trainingDataDto.startTime() != null) {
            training.setStartTime(trainingDataDto.startTime());
        }

        if (trainingDataDto.endTime() != null) {
            training.setEndTime(trainingDataDto.endTime());
        }

        if (trainingDataDto.activityType() != null) {
            training.setActivityType(trainingDataDto.activityType());
        }

        training.setDistance(trainingDataDto.distance());

        training.setAverageSpeed(trainingDataDto.averageSpeed());

        if (user != null) {
            training.setUser(user);
        }

        return training;
    }
}
