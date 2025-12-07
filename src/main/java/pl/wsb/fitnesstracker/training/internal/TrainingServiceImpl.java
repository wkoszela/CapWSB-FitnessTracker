package pl.wsb.fitnesstracker.training.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    public List<Training> findAllTrainings() {
        log.info("Retrieving all trainings");
        return trainingRepository.findAll();
    }

    public List<Training> findTrainingsByUserId(Long userId) {
        log.info("Retrieving trainings for user with ID: {}", userId);
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public Optional<Training> getTraining(Long trainingId) {
        log.info("Retrieving training with ID: {}", trainingId);
        return trainingRepository.findById(trainingId);
    }
}
