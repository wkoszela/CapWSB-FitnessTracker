package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

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

}