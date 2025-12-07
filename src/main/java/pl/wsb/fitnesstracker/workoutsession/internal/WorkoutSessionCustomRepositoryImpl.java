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