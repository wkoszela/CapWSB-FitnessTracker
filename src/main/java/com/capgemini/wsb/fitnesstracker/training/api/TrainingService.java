package com.capgemini.wsb.fitnesstracker.training.api;

import java.util.List;

public interface TrainingService {
    List<Training> getAllTrainings();
    List<Training> getUserTraining(Long userid);
    List<Training> getCompletedTraining(Long userid);
}
