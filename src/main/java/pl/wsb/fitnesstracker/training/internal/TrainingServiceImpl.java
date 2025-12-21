package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final UserProvider userProvider;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    public List<Training> getFinishedTrainingsAfter(Date afterTime) {
        return trainingRepository.findByEndTimeAfter(afterTime);
    }

    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    // Tu można dodać metodę createTraining(TrainingCreateDto dto)
}