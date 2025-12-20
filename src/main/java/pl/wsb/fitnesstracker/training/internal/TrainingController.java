package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller that provides endpoints for creating and retrieving
 * training sessions.
 *
 * <p>All routes are prefixed with {@code /v1/trainings}.</p>
 *
 * <h3>Endpoints</h3>
 * <ul>
 *   <li><b>GET /v1/trainings</b> – returns all trainings.</li>
 *   <li><b>GET /v1/trainings/{userId}</b> – returns all trainings for a given user.</li>
 *   <li><b>POST /v1/trainings/user/{userId}</b> – creates a new training for the specified user.</li>
 * </ul>
 *
 * <p>Only HTTP status codes are returned for create and read operations –
 * the body contains the DTOs or nothing (for POST).</p>
 *
 * @author Your Name
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;
    private final TrainingMapper mapper;
    private final UserService userService;

    /** GET /v1/trainings */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    /** GET /v1/trainings/{userId} */
    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsForUser(@PathVariable Long userId) {
        User user = userService.getUser(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return trainingService.getTrainingsByUser(user).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    /** POST /v1/trainings/user/{userId} */
    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTraining(@PathVariable Long userId, @RequestBody TrainingDto dto) {
        User user = userService.getUser(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Training training = mapper.toEntity(dto, user);
        trainingService.saveTraining(training);
    }
}
