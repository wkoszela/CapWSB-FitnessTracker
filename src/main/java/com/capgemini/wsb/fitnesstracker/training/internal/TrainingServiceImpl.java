package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final TrainingMapper trainingMapper;

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsFinishedAfter(Date date) {
        return trainingRepository.findByEndTimeAfter(date);
    }

    public List<Training> getTrainingsForUser(final Long userId) {
        Optional<User> requestedUser = userRepository.findById(userId);

        if (requestedUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        return trainingRepository.findByUser(requestedUser.get());
    }

    @Override
    public List<Training> getByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    @Override
    public Training createTraining(TrainingDto trainingDto) {
        User user = userRepository.getReferenceById(trainingDto.userId());

        return trainingRepository.save(
                trainingMapper.toEntity(trainingDto, user)
        );
    }

    @Override
    public Training updateTraining(Long trainingId, TrainingDto trainingDto) {
        User trainingUser = userRepository.getReferenceById(trainingDto.userId());

        Training updatedTraining = trainingMapper.toUpdateEntity(
                trainingDto,
                trainingRepository.getReferenceById(trainingId),
                trainingUser
        );

        return trainingRepository.save(updatedTraining);
    }
}
