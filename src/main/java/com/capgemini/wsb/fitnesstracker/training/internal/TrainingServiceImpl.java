package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

}
