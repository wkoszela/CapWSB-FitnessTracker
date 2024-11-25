package pl.wsb.fitnesstracker.training.internal;

import pl.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

interface TrainingRepository extends JpaRepository<Training, Long> {

}
