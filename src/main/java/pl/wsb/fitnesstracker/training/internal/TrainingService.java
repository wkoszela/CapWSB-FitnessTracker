package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for {@link Training} operations.
 *
 * <p>All methods are intentionally straightforward because the domain
 * logic is simple – data retrieval and persistence.  If future requirements
 * introduce more sophisticated validation or calculation, they belong here.</p>
 */
@Service
@RequiredArgsConstructor
public class TrainingService {

    private final TrainingRepository repository;

    /** Returns all trainings in the database. */
    public List<Training> getAllTrainings() {
        return repository.findAll();
    }

    /** Returns trainings belonging to the supplied user. */
    public List<Training> getTrainingsByUser(User user) {
        return repository.findAll().stream()
                .filter(t -> t.getUser().getId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    /** Persists a training and returns the managed entity. */
    public Training saveTraining(Training training) {
        return repository.save(training);
    }
}
