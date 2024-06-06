package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDTO;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private TrainingMapper trainingMapper;

    @Autowired
    private UserService userService;

    private User findUserById(Long userId) {
        return userService.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
    /**
     * Retrieves all trainings.
     *
     * @return the list of trainings
     */
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }
    /**
     * Retrieves all trainings for a specific user.
     *
     * @param userId the ID of the user
     * @return the list of trainings for the user
     */
    public List<Training> findTrainingsByUser(Long userId) {
        return trainingRepository.findByUserId(userId);
    }
    /**
     * Retrieves all completed trainings before a specific date.
     *
     * @param endDate the end date
     * @return the list of completed trainings
     */
    public List<Training> findCompletedTrainings(Date endDate) {
        return trainingRepository.findByEndTimeAfter(endDate);
    }
    /**
     * Retrieves all trainings by activity type.
     *
     * @param activityType the type of activity
     * @return the list of trainings with the specified activity type
     */
    public List<Training> findTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    /**
     * Creates a new training.
     *
     * @param trainingDTO the training data transfer object
     * @return the created training
     */
    public Training createTraining(TrainingDTO trainingDTO) {
        User user = findUserById(trainingDTO.getUserId());
        Training training = trainingMapper.toEntity(trainingDTO, user);
        return trainingRepository.save(training);
    }

    /**
     * Updates an existing training.
     *
     * @param id the ID of the training to update
     * @param trainingDTO the training data transfer object
     * @return the updated training
     */
    public Optional<Training> updateTraining(Long id, TrainingDTO trainingDTO) {
        return trainingRepository.findById(id).map(training -> {
            User user = findUserById(trainingDTO.getUserId());
            trainingMapper.updateEntity(training, trainingDTO);
            training.setUser(user);
            return trainingRepository.save(training);
        });
    }
}