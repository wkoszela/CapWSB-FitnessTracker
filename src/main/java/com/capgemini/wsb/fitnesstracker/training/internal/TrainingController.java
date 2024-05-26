package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * Pobierz wszystkie treningi
     * @return lista wszystkich treningów
     */
    @GetMapping("/training/list")
    public List<TrainingDTO> getAllTrainings() {
        return trainingService.findAllTrainings();
    }

    /**
     * Pobierz treningi dla określonego użytkownika
     * @param userId identyfikator użytkownika
     * @return lista treningów dla użytkownika
     */
    @GetMapping("/training/user/{userId}")
    public List<TrainingDTO> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.findTrainingByUsersId(userId);
    }

    /**
     * Dodaj nowy trening
     * @param trainingDto dane treningu
     * @return odpowiedź z dodanym treningiem
     */
    @PostMapping("/training")
    public ResponseEntity<TrainingDTO> createTraining(@RequestBody TrainingDTO trainingDto) {
        return ResponseEntity.ok(trainingService.newTraining(trainingDto));
    }

    /**
     * Pobierz treningi zakończone po określonej dacie
     * @param endDate data zakończenia
     * @return lista treningów zakończonych po danej dacie
     */
    @GetMapping("/trainings/ended")
    public ResponseEntity<List<TrainingDTO>> getTrainingsEndedAfter(@RequestParam String endDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date end = formatter.parse(endDate);
            List<TrainingDTO> trainings = trainingService.findTrainingsEndendInTime(end);
            return ResponseEntity.ok(trainings);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Pobierz treningi według rodzaju aktywności
     * @param activityType rodzaj aktywności
     * @return lista treningów według rodzaju aktywności
     */
    @GetMapping("/trainings/by-activity")
    public ResponseEntity<List<TrainingDTO>> getTrainingsByActivity(@RequestParam ActivityType activityType) {
        List<TrainingDTO> trainings = trainingService.findByTrainingsType(activityType);
        return ResponseEntity.ok(trainings);
    }

    /**
     * Zaktualizuj trening
     * @param id identyfikator treningu
     * @param trainingDto dane do aktualizacji
     * @return odpowiedź z zaktualizowanym treningiem
     */
    @PatchMapping("/trainings/{id}")
    public ResponseEntity<TrainingDTO> updateTraining(@PathVariable Long id, @RequestBody TrainingDTO trainingDto) {
        try {
            TrainingDTO updatedTraining = trainingService.updateTraining(id, trainingDto);
            return ResponseEntity.ok(updatedTraining);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
