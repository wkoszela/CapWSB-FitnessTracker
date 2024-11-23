package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public TrainingDto toDto(Training training) {
        if (training == null) {
            return null;
        }

        Long userId = null;
        if (training.getUser() != null) {
            userId = training.getUser().getId();
        }

        return new TrainingDto(
                training.getId(),
                userId,
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    public Training toEntity(TrainingDto dto, User user) {
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

    public Training toUpdateEntity(TrainingDto trainingDto, Training training, User user) {
        if (trainingDto == null || training == null) {
            return training;
        }

        if (trainingDto.startTime() != null) {
            training.setStartTime(trainingDto.startTime());
        }

        if (trainingDto.endTime() != null) {
            training.setEndTime(trainingDto.endTime());
        }

        if (trainingDto.activityType() != null) {
            training.setActivityType(trainingDto.activityType());
        }

        training.setDistance(trainingDto.distance());

        training.setAverageSpeed(trainingDto.averageSpeed());

        if (user != null) {
            training.setUser(user);
        }

        return training;
    }
}
