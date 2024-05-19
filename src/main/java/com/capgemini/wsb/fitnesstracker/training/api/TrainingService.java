package com.capgemini.wsb.fitnesstracker.training.api;

public interface TrainingService {

    Training createTraining(CreateTrainingRequest trainingRequest);

    Training updateTraining(long trainingId, CreateTrainingRequest createTrainingRequest);

    void deleteTraining(long trainingId);
}
