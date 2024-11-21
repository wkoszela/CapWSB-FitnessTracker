package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService, TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;

    @Override
    public Training createTraining(Training training) {
        log.info("Creating Training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training already has an ID, cannot create!");
        }
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, UpdateTrainingDto trainingDto) {
        Optional<Training> existingTrainingOpt = trainingRepository.findById(id);
        if (existingTrainingOpt.isEmpty()) {
            return null;
        }
        Training existingTraining = existingTrainingOpt.get();

        existingTraining.setStartTime(trainingDto.startTime());
        existingTraining.setEndTime(trainingDto.endTime());
        existingTraining.setActivityType(trainingDto.activityType());
        existingTraining.setDistance(trainingDto.distance());
        existingTraining.setAverageSpeed(trainingDto.averageSpeed());

        if (trainingDto.userId() != null) {
            Optional<User> userOpt = userProvider.getUser(trainingDto.userId());
            if (userOpt.isEmpty()) {
                throw new IllegalArgumentException("User with ID=" + trainingDto.userId() + " not found");
            }
            existingTraining.setUser(userOpt.get());
        }

        return trainingRepository.save(existingTraining);
    }

    @Override
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }

    @Override
    public Optional<Training> getTraining(Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public List<Training> getFinishedTrainingsAfter(Date afterTime) {
        return trainingRepository.findByEndTimeAfter(afterTime);
    }

    @Override
    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }
}
