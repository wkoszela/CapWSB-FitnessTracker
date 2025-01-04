package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingUpdateDto;

public interface TrainingService {

    Training createTraining(TrainingUpdateDto training);

    Training updateTraining(Long id, TrainingUpdateDto training);

}