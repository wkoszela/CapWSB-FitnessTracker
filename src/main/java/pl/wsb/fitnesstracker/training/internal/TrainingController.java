package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Training> getTraining(@PathVariable Long id) {
        return trainingService.getTraining(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Training> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/{userId}")
    public List<Training> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId);
    }

    @GetMapping("/finished/{afterTime}")
    public List<Training> getCompletedTrainingsAfterDate(@PathVariable String afterTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(afterTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
        return trainingService.getCompletedTrainingsAfterDate(date);
    }

    @GetMapping("/activityType")
    public List<Training> getTrainingsByActivityType(@RequestParam String activityType) {
        return trainingService.getTrainingsByActivityType(activityType);
    }

    @PostMapping
    public ResponseEntity<Training> createTraining(@RequestBody TrainingDto request) {
        Training training = trainingMapper.toEntity(request);
        Training created = trainingService.createTraining(training);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{trainingId}")
    public Training updateTraining(@PathVariable Long trainingId, @RequestBody TrainingDto request) {
        Training training = trainingMapper.toEntity(request);
        training.setId(trainingId);
        return trainingService.updateTraining(training);
    }

}