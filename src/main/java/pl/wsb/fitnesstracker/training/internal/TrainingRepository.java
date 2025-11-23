package pl.wsb.fitnesstracker.training.internal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.List;

@Repository
public class TrainingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Training> findByDistanceGreaterThan(double distance) {
        return entityManager.createQuery(
                        "SELECT t FROM Training t WHERE t.distance > :distance",
                        Training.class
                ).setParameter("distance", distance)
                .getResultList();
    }
}
