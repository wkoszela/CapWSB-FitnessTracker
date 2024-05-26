package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {
    @Autowired
    private final UserMapper userMapper;

    public TrainingMapper(UserMapper userMapper){this.userMapper =userMapper;}




    TrainingDTO toDto(Training training){
      return new TrainingDTO(training.getId(),
              userMapper.toDto(training.getUser()),
              training.getStartTime(),
              training.getDistance(),
              training.getEndTime(),
              training.getActivityType(),
              training.getAverageSpeed()
      );

    }
    Training toEntity(TrainingDTO trainingDTO) {
        return new Training(trainingDTO.getId(),
                userMapper.toEntity(trainingDTO.getUser()),
                trainingDTO.getEndTime(),
                trainingDTO.getActivityType(),
                trainingDTO.getAvarangeSpeed(),
                trainingDTO.getDistance(),
                trainingDTO.getStartTime()
        );
    }


}
