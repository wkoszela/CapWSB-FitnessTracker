package com.capgemini.wsb.fitnesstracker.training.internal;


import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

TrainingDto toDto(Training training) {
    UserDto userDto = new UserDto(
            training.getUser().getId(),
            training.getUser().getFirstName(),
            training.getUser().getLastName(),
            training.getUser().getBirthdate(),
            training.getUser().getEmail()
    );

    TrainingDto trainingDto = new TrainingDto(
            training.getId(),
            userDto,
            training.getStartTime(),
            training.getEndTime(),
            training.getActivityType(),
            training.getDistance(),
            training.getAverageSpeed()
    );
    return trainingDto;
}

}
