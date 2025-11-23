package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkoutSessionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<WorkoutSession> findByTrainingId(Long trainingId) {
        return entityManager.createQuery(
                        "SELECT ws FROM WorkoutSession ws WHERE ws.training.id = :trainingId",
                        WorkoutSession.class
                ).setParameter("trainingId", trainingId)
                .getResultList();
    }
}
