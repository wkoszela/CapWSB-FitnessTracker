package pl.wsb.fitnesstracker.statistics.internal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.statistics.api.Statistics;

@Repository
public class StatisticsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Statistics findByUserId(Long userId) {
        return entityManager.createQuery(
                        "SELECT s FROM Statistics s WHERE s.user.id = :userId",
                        Statistics.class
                ).setParameter("userId", userId)
                .getSingleResult();
    }
}
