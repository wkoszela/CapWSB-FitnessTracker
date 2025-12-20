package pl.wsb.fitnesstracker.training.internal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;

import java.util.List;

/**
 * TrainingController is responsible for handling HTTP requests related to training operations.
 */
@RestController
@RequestMapping("/v1/trainings")
class TrainingController {

    private final TrainingProvider trainingProvider;
    private final TrainingMapper trainingMapper;

    TrainingController(TrainingProvider trainingProvider, TrainingMapper trainingMapper) {
        this.trainingProvider = trainingProvider;
        this.trainingMapper = trainingMapper;
    }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingProvider.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable("userId") Long userId) {
        return trainingProvider.findTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}
