package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings().stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId).stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getFinishedTrainings(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        return trainingService.getFinishedTrainingsAfter(afterTime).stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.getTrainingsByActivityType(activityType).stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}