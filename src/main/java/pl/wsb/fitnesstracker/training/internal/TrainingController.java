package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingNotFoundException;

import java.util.List;

/**
 * REST Controller dla API treningów.
 *
 * Mapuje HTTP żądania na operacje biznesowe.
 * Base path: /v1/trainings
 *
 * @author Fitness Tracker Team
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * GET /v1/trainings
     * Pobiera listę wszystkich treningów.
     *
     * @return Lista wszystkich treningów w formie TrainingDto
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings().stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * GET /v1/trainings/simple
     * Pobiera uproszczoną listę wszystkich treningów.
     *
     * @return Lista treningów w uproszczonej formie (ID, userId, activityType)
     */
    @GetMapping("/simple")
    public List<TrainingSimpleDto> getAllSimpleTrainings() {
        return trainingService.getAllTrainings().stream()
                .map(trainingMapper::toSimpleDto)
                .toList();
    }

    /**
     * GET /v1/trainings/details/{id}
     * Pobiera szczegóły konkretnego treningu.
     *
     * @param id ID treningu
     * @return TrainingDto z danymi treningu
     * @throws TrainingNotFoundException Jeśli trening nie istnieje
     */
    @GetMapping("/details/{id}")
    public TrainingDto getTrainingById(@PathVariable Long id) {
        return trainingService.getTraining(id)
                .map(trainingMapper::toDto)
                .orElseThrow(() -> new TrainingNotFoundException(id));
    }

    /**
     * GET /v1/trainings/{userId}
     * Pobiera treningi konkretnego użytkownika.
     *
     * @param userId ID użytkownika
     * @return Lista treningów użytkownika w formie TrainingDto
     * @throws pl.wsb.fitnesstracker.user.api.UserNotFoundException Jeśli użytkownik nie istnieje
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId).stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * POST /v1/trainings
     * Tworzy nowy trening.
     *
     * @param trainingDto DTO z danymi nowego treningu
     * @return TrainingDto z utworzonym treningiem (zawiera ID)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingDto trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        Training savedTraining = trainingService.createTraining(training);
        return trainingMapper.toDto(savedTraining);
    }

    /**
     * PUT /v1/trainings/{id}
     * Aktualizuje istniejący trening.
     *
     * @param id ID treningu do aktualizacji
     * @param trainingDto DTO ze zmianami
     * @return TrainingDto z zaktualizowanym treningiem
     * @throws TrainingNotFoundException Jeśli trening nie istnieje
     */
    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable Long id,
                                      @RequestBody TrainingDto trainingDto) {
        Training existingTraining = trainingService.getTraining(id)
                .orElseThrow(() -> new TrainingNotFoundException(id));

        // Aktualizujemy tylko pola, które są nie-null w DTO
        if (trainingDto.getStartTime() != null) {
            existingTraining.setStartTime(trainingDto.getStartTime());
        }
        if (trainingDto.getEndTime() != null) {
            existingTraining.setEndTime(trainingDto.getEndTime());
        }
        if (trainingDto.getActivityType() != null) {
            existingTraining.setActivityType(trainingDto.getActivityType());
        }
        if (trainingDto.getDistance() > 0) {
            existingTraining.setDistance(trainingDto.getDistance());
        }
        if (trainingDto.getAverageSpeed() > 0) {
            existingTraining.setAverageSpeed(trainingDto.getAverageSpeed());
        }

        Training updated = trainingService.updateTraining(existingTraining, id);
        return trainingMapper.toDto(updated);
    }

    /**
     * DELETE /v1/trainings/{id}
     * Usuwa trening.
     *
     * @param id ID treningu do usunięcia
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
    }
}

