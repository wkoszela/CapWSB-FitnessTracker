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
import java.util.Optional;

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
        var user = userProvider.getUserEntity(trainingInputDto.getUserId());
        if(user.isEmpty()){
            throw new NotFoundException("User not found");
        }
        TrainingDto trainingDto = trainingMapper.inputDtoToTrainingDto(user.get(), trainingInputDto);

        Training training = trainingMapper.toEntity(trainingDto);

        return trainingMapper.toDto(trainingRepository.save(training));
    }

    private Boolean isFinished(Training training, LocalDate date){
        LocalDate trainingDate = training.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return date.isBefore(trainingDate);
    }


}
