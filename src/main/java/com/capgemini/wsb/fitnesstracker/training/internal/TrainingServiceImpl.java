package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
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

    /**
     * Retrieves a training based on their ID.
     *
     * @param  trainingId  the ID of the training to be searched
     * @return             an Optional containing the located User, or Optional.empty() if not found
     * @throws UnsupportedOperationException if the method is not yet implemented
     */
    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    /**
     * Retrieves a training by its ID.
     *
     * @param  id  the ID of the training to be searched
     * @return     an Optional containing the located Training, or Optional.empty() if not found
     */
    @Override
    public Optional<Training> getById(Long id) {
        return trainingRepository.findById(id);
    }

    /**
     * Retrieves all trainings.
     *
     * @return A list of all trainings.
     */
    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Creates a new training entity.
     *
     * @param  training  the Training object to be created
     * @return           the created Training object
     */
    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    /**
     * Retrieves a list of trainings associated with the given user ID.
     *
     * @param  userId  the ID of the user
     * @return         a list of trainings associated with the user
     */
    @Override
    public List<Training> getTrainingsByUser(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    /**
     * Retrieves a list of trainings that end after the specified date.
     *
     * @param  date  the date to compare against the end time of trainings
     * @return       a list of trainings that end after the specified date
     */
    @Override
    public List<Training> getTrainingsEndingAfter(Date date) {
        return trainingRepository.findByEndTimeAfter(date);
    }

    /**
     * Retrieves a list of trainings based on the specified activity type.
     *
     * @param  type  the activity type to filter the trainings by
     * @return       a list of trainings matching the specified activity type
     */
    @Override
    public List<Training> getTrainingsByType(ActivityType type) {
        return trainingRepository.findByActivityType(type);
    }

    /**
     * Updates a training entity in the repository.
     *
     * @param  training  the Training object to be updated
     * @return           the updated Training object
     */
    @Override
    public Training updateTraining(Training training) {
        return trainingRepository.save(training);
    }
}
