package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
@Tag(name = "Training Management System")
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @Operation(summary = "View a list of all trainings")
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @Operation(summary = "View a list of trainings for a specific user")
    @GetMapping("/user/{userId}")
    public List<TrainingDto> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.findTrainingsByUserId(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @Operation(summary = "View a list of completed trainings with a specific date")
    @GetMapping("/completed")
    public List<TrainingDto> getCompletedTrainings(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return trainingService.findCompletedTrainings(date)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @Operation(summary = "View a list of trainings for a specific activity type")
    @GetMapping("/activity")
    public List<TrainingDto> getTrainingsByActivity(@RequestParam ActivityType activityType) {
        return trainingService.findTrainingsByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @Operation(summary = "Create a new training")
    @PostMapping
    public TrainingDto addTraining(@RequestBody TrainingDto trainingDto) {
        return trainingMapper.toDto(trainingService.createTraining(trainingMapper.toEntity(trainingDto)));
    }

    @Operation(summary = "Update an existing training")
    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable Long id, @RequestBody TrainingDto trainingDto) {
        return trainingMapper.toDto(trainingService.updateTraining(id, trainingDto));
    }
}