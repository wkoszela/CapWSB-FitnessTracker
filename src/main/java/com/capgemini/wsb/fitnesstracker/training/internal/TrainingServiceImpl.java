package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;

    private final TrainingMapper trainingMapper;

    private final UserProvider userProvider;


    @Override
    public List<TrainingDto> getAllTrainings(){
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @Override
    public List<TrainingDto> getTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @Override
    public List<TrainingDto> getTrainingsFinishedAfter(LocalDate date) {
        return trainingRepository.findAll()
                .stream()
                .filter(s -> isFinished(s, date))
                .map(trainingMapper::toDto)
                .toList();
    }

    @Override
    public List<TrainingDto> getTrainingsOfActivityType(String activityType) {
        return trainingRepository.findAll()
                .stream()
                .filter(s -> activityType.equalsIgnoreCase(s.getActivityType().toString()))
                .map(trainingMapper::toDto)
                .toList();
    }

    @Override
    public TrainingDto createTraining(TrainingInputDto trainingInputDto) {
        log.info("Creating Training {}", trainingInputDto);
        var user = userProvider.getUserEntity(trainingInputDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        TrainingDto trainingDto = trainingMapper.inputDtoToTrainingDto(user, trainingInputDto);

        Training training = trainingMapper.toEntity(trainingDto);

        return trainingMapper.toDto(trainingRepository.save(training));
    }

    private Boolean isFinished(Training training, LocalDate date){
        LocalDate trainingDate = training.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return date.isBefore(trainingDate);
    }

    @Override
    public TrainingDto updateTraining(final Long id, TrainingInputDto trainingInputDto){

        var training = trainingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Training not found"));

        var user = userProvider.getUserEntity(trainingInputDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        TrainingDto trainingDto = trainingMapper.inputDtoToTrainingDto(user, trainingInputDto);

        // Create updated dto and update training based on it
        TrainingDto oldTrainingDto = trainingMapper.toDto(training);
        TrainingDto newTrainingDto = oldTrainingDto.updateTraining(trainingDto).addId(id);
        Training newTraining = trainingMapper.toEntity(newTrainingDto);
        newTraining.setId(id);

        log.info("Updating Training {}", newTraining);
        trainingRepository.save(newTraining);
        return newTrainingDto;

    }

}
