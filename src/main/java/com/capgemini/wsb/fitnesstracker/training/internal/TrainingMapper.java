package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDTO;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public Training toEntity(TrainingDTO trainingDTO, User user) {
        return new Training(
                user,
                trainingDTO.getStartTime(),
                trainingDTO.getEndTime(),
                trainingDTO.getActivityType(),
                trainingDTO.getDistance(),
                trainingDTO.getAverageSpeed()
        );
    }

    public TrainingDTO toDTO(Training training) {
        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setUserId(training.getUser().getId());
        trainingDTO.setStartTime(training.getStartTime());
        trainingDTO.setEndTime(training.getEndTime());
        trainingDTO.setActivityType(training.getActivityType());
        trainingDTO.setDistance(training.getDistance());
        trainingDTO.setAverageSpeed(training.getAverageSpeed());
        return trainingDTO;
    }

    public void updateEntity(Training training, TrainingDTO trainingDTO) {
        training.setStartTime(trainingDTO.getStartTime());
        training.setEndTime(trainingDTO.getEndTime());
        training.setActivityType(trainingDTO.getActivityType());
        training.setDistance(trainingDTO.getDistance());
        training.setAverageSpeed(trainingDTO.getAverageSpeed());
    }
}