package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;

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

    @GetMapping("/user/{userId}")
    public List<Training> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId);
    }

    @GetMapping("/completed")
    public List<Training> getCompletedTrainingsAfterDate(@RequestParam Date date) {
        return trainingService.getCompletedTrainingsAfterDate(date);
    }

    @GetMapping("/activity/{type}")
    public List<Training> getTrainingsByActivityType(@PathVariable String type) {
        return trainingService.getTrainingsByActivityType(type);
    }

    @PostMapping
    public Training createTraining(@RequestBody Training training) {
        return trainingService.createTraining(training);
    }

    @PutMapping
    public Training updateTraining(@RequestBody Training training) {
        return trainingService.updateTraining(training);
    }


}
