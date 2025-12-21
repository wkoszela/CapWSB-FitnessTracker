package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.Date;
import java.util.List;

/**
 * Kontroler REST obsługujący operacje na treningach.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * Pobiera listę wszystkich treningów.
     *
     * @return lista wszystkich treningów jako DTO
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings().stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Pobiera listę treningów dla konkretnego użytkownika.
     *
     * @param userId ID użytkownika
     * @return lista treningów użytkownika jako DTO
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId).stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Pobiera listę treningów zakończonych po określonej dacie.
     *
     * @param afterTime data graniczna
     * @return lista zakończonych treningów jako DTO
     */
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getFinishedTrainings(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        return trainingService.getFinishedTrainingsAfter(afterTime).stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Pobiera listę treningów o określonym typie aktywności.
     *
     * @param activityType typ aktywności
     * @return lista treningów o danym typie jako DTO
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.getTrainingsByActivityType(activityType).stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}