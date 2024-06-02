package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class TrainingMapper {
    Training toEntity(TrainingDtoWithUserId trainingDto, User user){
        if(trainingDto.id() == null) {
            return new Training(user, trainingDto.startTime(),
                    trainingDto.endTime(), trainingDto.activityType(),
                    trainingDto.distance(), trainingDto.averageSpeed()
            );
        }
        else{
            return new Training(trainingDto.id(), user, trainingDto.startTime(),
                    trainingDto.endTime(), trainingDto.activityType(),
                    trainingDto.distance(), trainingDto.averageSpeed()
            );
        }
    }
    static TrainingDtoWithUserDto toDtoWithUserDto(Training training){
        return new TrainingDtoWithUserDto(
                training.getId(), UserMapper.toDto(training.getUser()), training.getStartTime(),
                training.getEndTime(), training.getActivityType(),
                training.getDistance(), training.getAverageSpeed()
        );
    }
public Training toEntity(Long id, TrainingDtoForPut newTraining, Training trainingDto, User user) {
    Date startTime = (newTraining.startTime() != null) ? newTraining.startTime() : trainingDto.getStartTime();
    Date endTime = (newTraining.endTime() != null) ? newTraining.endTime() : trainingDto.getEndTime();
    ActivityType activityType = (newTraining.activityType() != null) ? newTraining.activityType() : trainingDto.getActivityType();
    Double distance = (newTraining.distance() != null) ? newTraining.distance() : trainingDto.getDistance();
    Double avgSpeed = (newTraining.averageSpeed() != null) ? newTraining.averageSpeed() : trainingDto.getAverageSpeed();

    return new Training(id, user, startTime, endTime, activityType, distance, avgSpeed);
}

}