package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

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
    public List<Training> getUserTraining(Long userId) {
        return trainingRepository.findByUser_Id(userId);
    }

    @Override
    public List<Training> getCompletedTraining(Date endDate) {
        return trainingRepository.findByEndTimeAfter(endDate);
    }

    @Override
    public List<Training> getTrainingByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }
    @Override
    public List<Training> getFinishedTrainingsAfter(Date afterTime) {
        return trainingRepository.findByEndTimeAfter(afterTime);
    }
}
