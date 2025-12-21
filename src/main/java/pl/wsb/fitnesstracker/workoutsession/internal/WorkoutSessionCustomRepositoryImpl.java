// This code snippet is defining a Java interface named `WorkoutSessionCustomRepository` in the package
// `pl.wsb.fitnesstracker.workoutsession.internal`. The interface declares a method
// `findSessionsByTrainingId` that is intended to execute a custom JPQL query to find workout sessions
// related to a specific training identified by its ID. The method returns a list of `WorkoutSession`
// objects. This interface is likely part of a larger system for managing workout sessions within a
// fitness tracker application.
package pl.wsb.fitnesstracker.workoutsession.internal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.workoutsession.WorkoutSession;

import java.util.List;

@Repository
class WorkoutSessionCustomRepositoryImpl implements WorkoutSessionCustomRepository {

    // 1. Wstrzyknięcie EntityManagera (wymóg LAB03)
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WorkoutSession> findSessionsByTrainingId(Long trainingId) {
        // 2. Zbudowanie zapytania JPQL (wymóg LAB03)
        // "SELECT s FROM WorkoutSession s" to odpowiednik "SELECT * FROM
        // workout_session" w SQL
        String jpql = "SELECT s FROM WorkoutSession s WHERE s.training.id = :trainingId";

        // 3. Wywołanie createQuery (wskazówka prowadzącego)
        return entityManager.createQuery(jpql, WorkoutSession.class)
                .setParameter("trainingId", trainingId)
                .getResultList();
    }
}