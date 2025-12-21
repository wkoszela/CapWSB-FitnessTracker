// This code snippet is defining a Java interface `WorkoutSessionRepository` in the package
// `pl.wsb.fitnesstracker.workoutsession.internal`. The interface extends two other interfaces:
// `JpaRepository<WorkoutSession, Long>` from the Spring Data JPA framework and
// `WorkoutSessionCustomRepository`, which is a custom repository interface.
package pl.wsb.fitnesstracker.workoutsession.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.workoutsession.WorkoutSession;

// Rozszerzamy JpaRepository (standard) ORAZ nasze CustomRepository (punkt 5)
interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long>, WorkoutSessionCustomRepository {
}