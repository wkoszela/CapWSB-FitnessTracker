package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    private UserProvider userProvider;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> getAllTrainings() {

        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUser(long userId) {

        if (userProvider.getUserById(userId).isEmpty()) {
            throw new IllegalArgumentException("User with given ID not found");
        }
        return this.trainingRepository.findTrainingByUser_Id(userId);
    }

    @Override
    public List<Training> getAllTrainingsFinishedAfter(Date afterTime) {
        List<Training> allTrainings = trainingRepository.findAll().stream()
                .filter(training -> training.getEndTime().after(afterTime)).toList();

        if (allTrainings.isEmpty()) {
            throw new NotFoundException("No trainings finished after given time found");
        }

        return allTrainings;
    }

    @Override
    public List<Training> getAllTrainingsByActivityType(ActivityType activityType) {

        List<Training> allTrainings = trainingRepository.findByActivityType(activityType);
        if (allTrainings.isEmpty()) {
            throw new NotFoundException("No trainings with given activity type found");
        }
        return allTrainings;
    }

    public Training createTraining(CreateTrainingRequest trainingRequest) {

        User user = userProvider.getUserById(trainingRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("User with given ID not found"));

        Training newTraining = trainingMapper.toEntity(trainingRequest, user);
        return trainingRepository.save(newTraining);
    }

    @Override
    public Training updateTraining(long trainingId, CreateTrainingRequest createTrainingRequest) {

        Optional<Training> foundTraining = trainingRepository.findById(trainingId);

        if (foundTraining.isPresent()) {
            Training training = updateTrainingAttributes(createTrainingRequest, foundTraining);
            return trainingRepository.saveAndFlush(training);
        } else {
            throw new NotFoundException("Training with given ID not found");
        }

    }

    @Override
    public void deleteTraining(long trainingId) {

        Optional<Training> foundTraining = trainingRepository.findById(trainingId);

       if (foundTraining.isPresent()) {
    trainingRepository.delete(foundTraining.get());
} else {
    throw new NotFoundException("Training with given ID not found");
}
    }

    private Training updateTrainingAttributes(CreateTrainingRequest createTrainingRequest, Optional<Training> foundTraining) {

        Training training = foundTraining.get();
        training.setActivityType(createTrainingRequest.getActivityType());
        training.setStartTime(createTrainingRequest.getStartTime());
        training.setEndTime(createTrainingRequest.getEndTime());
        training.setDistance(createTrainingRequest.getDistance());
        training.setAverageSpeed(createTrainingRequest.getAverageSpeed());
        return training;

    }
}