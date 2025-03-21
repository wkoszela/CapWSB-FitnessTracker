package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingService trainingService;

    private final TrainingMapper trainingMapper;

    @GetMapping
    List<TrainingDto> findAllTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    List<TrainingDto> findTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.findTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/finished/{afterTime}")
    List<TrainingDto> findTrainingsFinishedAfter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")  Date afterTime) {
        return trainingService.findTrainingsWithEndDateAfter(afterTime)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activityType")
    List<TrainingDto> findTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.findTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PostMapping
    ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingShallowDto trainingDto) {
        Training persisted = trainingService.createTraining(trainingMapper.toEntity(trainingDto), trainingDto.getUserId());
        TrainingDto dto =  trainingMapper.toDto(persisted);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{trainingId}")
    TrainingDto updateTraining(@PathVariable Long trainingId, @RequestBody TrainingShallowDto trainingDto) {
        Training updated = trainingService.updateTraining(trainingMapper.toEntity(trainingDto), trainingId, trainingDto.getUserId());
        return trainingMapper.toDto(updated);
    }
}
