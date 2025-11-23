package pl.wsb.fitnesstracker.workoutsession.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.workoutsession.WorkoutSession;

// Rozszerzamy JpaRepository (standard) ORAZ nasze CustomRepository (punkt 5)
interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long>, WorkoutSessionCustomRepository {
}