package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingDTO;

import java.util.Date;
import java.util.List;

public interface TrainingService {

    List<TrainingDTO> findTrainingByUsersId(Long userId);
    List<TrainingDTO> findAllTrainings();
    List<TrainingDTO> findByTrainingsType(ActivityType activityType);
    List<TrainingDTO> findTrainingsEndendInTime(Date endDate);

    TrainingDTO newTraining(TrainingDTO trainingDTO);
    TrainingDTO updateTraining (Long id, TrainingDTO trainingDTO);


}
