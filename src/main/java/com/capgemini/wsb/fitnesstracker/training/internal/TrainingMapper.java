package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.CreateTrainingRequest;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * TrainingMapper is a class that is responsible for mapping Training objects to TrainingDto objects and vice versa.
 *
 */
@Component
@AllArgsConstructor
class TrainingMapper {

    private UserMapper userMapper;

    /**
     * Maps Training entity object to TrainingDto
     * @param training Training entity object
     * @return TrainingDto object
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    /**
     * Maps TrainingDto to Training entity object
     * @param trainingDto TrainingDto object
     * @return Training entity object
     */
    public Training toEntity(TrainingDto trainingDto) {
        return new Training(
                userMapper.toEntity(trainingDto.getUser()),
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed());
    }

   public Training toEntity(CreateTrainingRequest createTrainingRequest, User user) {
        return new Training(
                user,
                createTrainingRequest.getStartTime(),
                createTrainingRequest.getEndTime(),
                createTrainingRequest.getActivityType(),
                createTrainingRequest.getDistance(),
                createTrainingRequest.getAverageSpeed());
    }
}
