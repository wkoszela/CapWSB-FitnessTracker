package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.training.api.Training;

/**
 * Repository interface for {@link Training} entities.
 * Provides CRUD operations and custom query methods related to trainings.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {
    /**
     * Deletes all trainings associated with a specific user ID.
     * This operation is transactional to ensure data integrity.
     *
     * @param userId the ID of the user whose trainings should be deleted
     */
    @Transactional
    void deleteByUser_Id(Long userId);
}
