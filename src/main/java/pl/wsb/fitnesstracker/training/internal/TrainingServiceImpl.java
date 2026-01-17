package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;

import java.util.Optional;

/**
 * Concrete implementation of {@link TrainingProvider}.
 *
 * <p>The service simply delegates the lookup to the JPA repository.
 * A {@link java.util.Optional#empty()} is returned if
 * {@code trainingId} is {@code null} or no entity with that id exists.</p>
 */
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingProvider {

    /** Repository that provides CRUD operations for {@link Training}. */
    private final TrainingRepository repository;

    /**
     * Fetches a training by its primary key.
     *
     * @param trainingId the id of the training to retrieve
     * @return {@code Optional.of(training)} if found, otherwise {@link Optional#empty()}
     */
    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        if (trainingId == null) {
            return Optional.empty();
        }
        return repository.findById(trainingId);
    }
}
