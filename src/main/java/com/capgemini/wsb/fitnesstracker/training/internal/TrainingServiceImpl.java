package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO: Provide Impl
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getAllTrainingsForUser(long userId) {
        return trainingRepository.findAll().stream().filter(training -> training.getUser().getId().equals(userId)).collect(Collectors.toList());
    }

    @Override
    public Training addTraining(Training training) {
        if(training.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, update is not permitted!");
        }
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getAllFinishTrainings(Date finishData){
        return trainingRepository.findAll().stream().filter(training -> training.getEndTime().after(finishData)).collect(Collectors.toList());
    }

    @Override
    public List<Training> getAllTrainingTypes(ActivityType activity) {
        return trainingRepository.findAll().stream().filter(training -> training.getActivityType().equals(activity)).collect(Collectors.toList());
    }

    public void updateActivity(long id, ActivityType newActivityType) {
        Training training = trainingRepository.getReferenceById(id);
        training.setActivityType(newActivityType);
    }
}
