package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;

    private final TrainingMapper trainingMapper;

    @Override
    public List<TrainingDto> findAllTrainings(){
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<TrainingDto> getTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @Override
    public List<TrainingDto> findTrainingsFinishedAfter(LocalDate date) {
        return trainingRepository.findAll()
                .stream()
                .filter(s -> isFinished(s, date))
                .map(trainingMapper::toDto)
                .toList();
    }

    private Boolean isFinished(Training training, LocalDate date){
        LocalDate trainingDate = training.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return date.isBefore(trainingDate);
    }
}
