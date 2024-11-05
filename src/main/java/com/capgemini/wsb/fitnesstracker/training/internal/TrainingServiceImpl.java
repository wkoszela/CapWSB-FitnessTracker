package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


// TODO: Provide Impl
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> getAllTrainings() {
        return List.of();
    }

    @Override
    public List<Training> getUserTraining(Long userid) {
        return List.of();
    }

    @Override
    public List<Training> getCompletedTraining(LocalDate endDate) {
        return List.of();
    }

    @Override
    public List<Training> getTrainingByActivity(String activityType) {
        return List.of();
    }
}
