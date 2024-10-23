package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
// TODO: Provide Impl
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId)
                .map(training -> training.getUser());
    }

    public void deleteTrainingByUserId(Long userId) {
        trainingRepository.findAll().stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .forEach(trainingRepository::delete);
    }

}