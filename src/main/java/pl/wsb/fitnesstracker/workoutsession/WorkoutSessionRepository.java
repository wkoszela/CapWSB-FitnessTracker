package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkoutSessionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public WorkoutSession save(WorkoutSession workoutSession) {
        if (workoutSession.getId() == null) {
            entityManager.persist(workoutSession);
            return workoutSession;
        } else {
            return entityManager.merge(workoutSession);
        }
    }

    public Optional<WorkoutSession> findById(Long id) {
        return Optional.ofNullable(entityManager.find(WorkoutSession.class, id));
    }

    public List<WorkoutSession> findAll() {
        return entityManager.createQuery("SELECT w FROM WorkoutSession w", WorkoutSession.class).getResultList();
    }

    @Transactional
    public void delete(WorkoutSession workoutSession) {
        if (entityManager.contains(workoutSession)) {
            entityManager.remove(workoutSession);
        } else {
            entityManager.remove(entityManager.merge(workoutSession));
        }
    }
}
