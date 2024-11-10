package com.capgemini.wsb.fitnesstracker.training.api;


import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TrainingService {
    List<Training> getAllTrainings();
    List<Training> getUserTraining(Long userid);
    List<Training> getCompletedTraining(Date endDate);
    List<Training> getTrainingByActivityType(ActivityType activityType);
    List<Training> getFinishedTrainingsAfter(Date afterTime);


}
