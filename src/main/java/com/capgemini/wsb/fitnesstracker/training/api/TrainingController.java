package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing trainings.
 */
@RestController
@RequestMapping("/v1/trainings")
public class TrainingController {

    @Autowired
    private TrainingService trainingService;
    /**
     * GET /v1/trainings : get all trainings.
     *
     * @return the list of trainings
     */

    @GetMapping
    public List<Training> getAllTrainings() {
        return trainingService.findAllTrainings();
    }
    /**
     * GET /v1/trainings/user/{userId} : get all trainings for a specific user.
     *
     * @param userId the id of the user
     * @return the list of trainings for the user
     */

    @GetMapping("/user/{userId}")
    public List<Training> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.findTrainingsByUser(userId);
    }
    /**
     * GET /v1/trainings/finished/{afterTime} : get all completed trainings after a specific date.
     *
     * @param afterTime the date after which trainings were completed
     * @return the list of completed trainings
     * @throws ParseException if the date format is invalid
     */
    @GetMapping("/finished/{afterTime}")
    public List<Training> getCompletedTrainings(@PathVariable String afterTime) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(afterTime);
        return trainingService.findCompletedTrainings(date);
    }
    /**
     * GET /v1/trainings/activityType : get all trainings by activity type.
     *
     * @param activityType the type of activity
     * @return the list of trainings with the specified activity type
     */
    @GetMapping("/activityType")
    public List<Training> getTrainingsByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.findTrainingsByActivityType(activityType);
    }
    /**
     * POST /v1/trainings : create a new training.
     *
     * @param trainingDTO the training to create
     * @return the ResponseEntity with status 201 (Created) and with body the new training, or with status 400 (Bad Request) if the training has already an ID
     */
    @PostMapping
    public ResponseEntity<Training> createTraining(@RequestBody TrainingDTO trainingDTO) {
        Training training = trainingService.createTraining(trainingDTO);
        return ResponseEntity.status(201).body(training);
    }
    /**
     * PUT /v1/trainings/{id} : update an existing training.
     *
     * @param id the id of the training to update
     * @param trainingDTO the training to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated training, or with status 404 (Not Found) if the training is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Training> updateTraining(@PathVariable Long id, @RequestBody TrainingDTO trainingDTO) {
        Optional<Training> updatedTraining = trainingService.updateTraining(id, trainingDTO);
        return updatedTraining.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}