package com.capgemini.wsb.fitnesstracker.training.api;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import java.util.Optional;
import java.util.Date;
import java.util.List;

public interface TrainingProvider {
    Optional<User> getAllTrainings(Long trainingId);
    List<Training> getTrainingsByUserId(Long userId);
    Optional<Training> getTrainingById(Long trainingId);
    List<Training> getTrainingsFinishedAfterX(Date dateTime);
}