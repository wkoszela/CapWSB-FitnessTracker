package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingDataDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingService {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTraining(Long trainingId);

    public List<Training> getAllTrainings();

    public List<Training> getTrainingsForUser(final Long userId);

    public List<Training> getTrainingsFinishedAfter(Date date);

    public List<Training> getByActivityType(ActivityType activityType);

    public Training createTraining(TrainingDataDto createTrainingDto);

    public Training updateTraining(Long trainingId, TrainingDataDto trainingDto);
}

