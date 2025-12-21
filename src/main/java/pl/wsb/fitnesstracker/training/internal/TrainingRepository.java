package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;
import java.util.List;
import java.util.Objects;

public interface TrainingRepository extends JpaRepository<Training, Long> {


    /**
     * szuka treninguw po id usera
     * @param userId
     * @return
     */
    default List<Training> findByUserId(Long userId) {
        return findAll().stream()
                .filter(t -> t.getUser().getId().equals(userId))
                .toList();
    }
}
