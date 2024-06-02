package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
import java.util.Optional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<User> getAllTrainings(final Long trainingId) {
        throw new UnsupportedOperationException("Trainings are not finished!");
    }
    @Override
    public Optional<Training>  getTrainingById(Long trainingId)
    {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public Training createTraining(Training training)
    {
        log.info("Create: {} training", training);
        if (training.getId() != null) {throw new IllegalArgumentException("This training ID is taken");}
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {return trainingRepository.getTrainingsByUserId(user.get());
        } else {return Collections.emptyList();}
    }
    @Override
    public List<Training> getTrainingsFinishedAfterX(Date dateTime)
    {return trainingRepository.getFinishedAfterX(dateTime);}
}