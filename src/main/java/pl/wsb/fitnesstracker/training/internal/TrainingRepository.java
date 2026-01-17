package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

/**
 * Repository for {@link Training} entities.
 *
 * <p>Extending {@code JpaRepository} provides standard CRUD operations
 * (findAll, findById, save, delete…​).  Custom queries can be added
 * by defining new methods that follow Spring Data naming conventions.</p>
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {
}
