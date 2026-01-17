package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.training.api.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    
}