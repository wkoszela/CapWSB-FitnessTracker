package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TreningController {
    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * szuka i pobiera dane wszystkich treningów
     * @return
     */
    @GetMapping
    List<TrainingDto> getAll() {
        return trainingService.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * szuka i pobiera dane wszystkich treningów danego Usera po jego id
     * @return
     */
    @GetMapping("/{userId}")
    List<TrainingDto> getByUser(@PathVariable Long userId) {
        return trainingService.findByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}
