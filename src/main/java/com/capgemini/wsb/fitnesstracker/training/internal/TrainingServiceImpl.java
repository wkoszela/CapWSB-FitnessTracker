package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

// TODO: Provide Impl
public class TrainingServiceImpl implements TrainingProvider, TrainingService {
    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }


    @Override
    public TrainingDTO createTraning(TrainingDTO trainingDto) {
        validateTrainingDoesNotExist(trainingDto);
        Optional<User> user = getValidatedUser(trainingDto.getUser().getId());

        Training training = trainingMapper.toEntity(trainingDto);
        user.ifPresent(training::setUser);

        Training savedTraining = trainingRepository.save(training);

        return trainingMapper.toDto(savedTraining);
    }

    @Override
    public List<TrainingDTO> findTrainingByUsersId(Long userId) {
        Optional<User> user = userService.getUser(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }

        return trainingRepository.findByUser(user).stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDTO> findAllTrainings() {
        return trainingRepository.findAll().stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDTO> findByTrainingsType(ActivityType activityType) {
        List<Training> trainings = trainingRepository.findByActivityType(activityType);

        return trainings.stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainingDTO> findTrainingsEndendInTime(Date endDate) {
        List<Training> trainings = trainingRepository.findAll();

        return trainings.stream()
                .filter(training -> training.getEndTime() != null && training.getEndTime().after(endDate))
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingDTO newTraining(TrainingDTO trainingDTO) {
        validateTrainingDoesNotExist(trainingDTO);
        Optional<User> user = getValidatedUser(trainingDTO.getUser().getId());

        Training training = trainingMapper.toEntity(trainingDTO);
        user.ifPresent(training::setUser);

        Training savedTraining = trainingRepository.save(training);

        return trainingMapper.toDto(savedTraining);
    }

    @Override
    public TrainingDTO updateTraining(Long id, TrainingDTO trainingDTO) {
        TrainingDTO existingTraining = validateTraining(trainingDTO);

        if (trainingDTO.getDistance() > 0 && trainingDTO.getDistance() != existingTraining.getDistance()) {
            existingTraining.setDistance(trainingDTO.getDistance());
        }

        if (trainingDTO.getEndTime() != null && !trainingDTO.getEndTime().equals(existingTraining.getEndTime())) {
            existingTraining.setEndTime(trainingDTO.getEndTime());
        }

        if (trainingDTO.getStartTime() != null && !trainingDTO.getStartTime().equals(existingTraining.getStartTime())) {
            existingTraining.setStartTime(trainingDTO.getStartTime());
        }

        if (trainingDTO.getActivityType() != null && !trainingDTO.getActivityType().equals(existingTraining.getActivityType())) {
            existingTraining.setActivityType(trainingDTO.getActivityType());
        }

        Training updatedTraining = trainingMapper.toEntity(existingTraining);

        if (trainingDTO.getUser() != null && trainingDTO.getUser().getId() != null) {
            updatedTraining.setUser(getValidatedUser(trainingDTO.getUser().getId()).get());
        } else {
            updatedTraining.setUser(getValidatedUser(existingTraining.getUser().getId()).get());
        }

        return trainingMapper.toDto(trainingRepository.save(updatedTraining));
    }

    /**
     * Sprawdź, czy trening o takim samym id już istnieje
     *
     * @param trainingDTO obiekt treningu
     */
    private TrainingDTO validateTraining(TrainingDTO trainingDTO) {
        Optional<Training> existingTraining = trainingRepository.findById(trainingDTO.getId());
        if (existingTraining.isEmpty()) {
            throw new IllegalArgumentException("Training with id " + trainingDTO.getId() + " does not exist");
        }

        return trainingMapper.toDto(existingTraining.get());
    }

    /**
     * Sprawdź, czy trening o takim samym id już istnieje
     *
     * @param trainingDTO obiekt treningu
     */
    private void validateTrainingDoesNotExist(TrainingDTO trainingDTO) {
        Optional<Training> existingTraining = trainingRepository.findById(trainingDTO.getId());
        if (existingTraining.isPresent()) {
            throw new IllegalArgumentException("Training with id " + trainingDTO.getId() + " already exists");
        }
    }

    /**
     * Pobierz użytkownika po id i sprawdź, czy istnieje
     *
     * @param userId id użytkownika
     * @return obiekt User
     */
    private Optional<User> getValidatedUser(Long userId) {
        Optional<User> user = userService.getUser(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with id " + userId + " not found");
        }

        return user;
    }
}