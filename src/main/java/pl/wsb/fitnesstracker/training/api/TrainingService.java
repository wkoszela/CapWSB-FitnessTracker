package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.TrainingUpdateDto;

public interface TrainingService {

    Training createTraining(TrainingUpdateDto training);

    Training updateTraining(Long id, TrainingUpdateDto training);

}