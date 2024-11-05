package com.capgemini.wsb.fitnesstracker.training.api;


import java.time.LocalDate;
import java.util.List;

public interface TrainingService {
    List<Training> getAllTrainings();
    List<Training> getUserTraining(Long userid);
    List<Training> getCompletedTraining(LocalDate endDate);
    List<Training> getTrainingByActivity(String activityType);

}
