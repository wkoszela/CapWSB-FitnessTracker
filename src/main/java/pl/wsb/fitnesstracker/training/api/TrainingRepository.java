package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Retrieves all trainings for the given user ID.
     *
     * @param userId ID of the user
     * @return list of trainings for the user
     */
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(training -> training.getUser() != null && userId.equals(training.getUser().getId()))
                .toList();
    }
}
