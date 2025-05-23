package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// TODO: Provide Implementation and correct the return type of the method getTraining
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    public List<Training> getAllTrainings() {
        return trainingRepository.findAllTrainings();
    }

    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findTrainingsByUserId(userId);
    }

    public List<Training> getCompletedTrainingsAfterDate(Date date) {
        return trainingRepository.findCompletedTrainingsAfterDate(date);
    }

    public List<Training> getTrainingsByActivityType(String activityType) {
        return trainingRepository.findTrainingsByActivityType(activityType);
    }

    public Training createTraining(Training training) {
        return trainingRepository.createTraining(training);
    }

    public Training updateTraining(Training training) {
        return trainingRepository.updateTraining(training);
    }






}
