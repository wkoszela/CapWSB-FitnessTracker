package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;



    @GetMapping
    public List<TrainingDto> findAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    @GetMapping("/user/{userId}")
    public List<TrainingDto> findTrainingByUserId(@PathVariable Long userId) {
        return trainingService.getUserTraining(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    @GetMapping("/completed")
    public List<TrainingDto> getCompletedTraining(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return trainingService.getCompletedTraining(date)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getAllFinishedTrainingsAfterTime(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date afterTime) {
        return trainingService.getFinishedTrainingsAfter(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.getTrainingByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingDto trainingDto) {
        Training newTraining = trainingMapper.toEntity(trainingDto);
        Training savedTraining = trainingService.createTraining(newTraining);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainingMapper.toDto(savedTraining));
    }

//    @PutMapping("/{trainingId}")
//    public ResponseEntity<TrainingDto> updateTraining(
//            @PathVariable Long trainingId,
//            @RequestBody TrainingDto updatedFields) {
//        Training updatedTraining = trainingService.createTraining(trainingId, updatedFields);
//        return ResponseEntity.ok(trainingMapper.toDto(updatedTraining));
    }




