package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
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

    return new TrainingDto(
            training.getId(),
            userDto,
            training.getStartTime(),
            training.getEndTime(),
            training.getActivityType(),
            training.getDistance(),
            training.getAverageSpeed()
    );
}
    Training toEntity(TrainingDto trainingDto) {
        User user = new User(
                trainingDto.getUser().firstName(),
                trainingDto.getUser().lastName(),
                trainingDto.getUser().birthdate(),
                trainingDto.getUser().email()
        );

        Training training = new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );

        return training;
    }
}

//{

//Training toEntity(TrainingDto trainingDto) {
//    User user = new User(
//            trainingDto.getUser().getId(),
//            trainingDto.getUser().getFirstName(),
//            trainingDto.getUser().getLastName(),
//            trainingDto.getUser().getBirthdate(),
//            trainingDto.getUser().getEmail()
//    );
//
//    Training training = new Training(
//            trainingDto.getId(),
//            user,
//            trainingDto.getStartTime(),
//            trainingDto.getEndTime(),
//            trainingDto.getActivityType(),
//            trainingDto.getDistance(),
//            trainingDto.getAverageSpeed()
//    );
//    return training;
//}

//}