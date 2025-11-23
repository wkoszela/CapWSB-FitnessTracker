package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class HealthMetricsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<HealthMetrics> findYoungerThanOneWeek() {
        LocalDate oneWeekAgo = LocalDate.now().minusWeeks(1);

        return entityManager.createQuery(
                        "SELECT h FROM HealthMetrics h WHERE h.date > :oneWeekAgo",
                        HealthMetrics.class
                ).setParameter("oneWeekAgo", oneWeekAgo)
                .getResultList();
    }
}
