package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Klasa odpowiadająca za zapytania SQL do HealhMetrics
 */
@Repository
public class HealthMetricsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<HealthMetrics> findYoungerThanOneWeek() {
        /**
        Zapytanie o młodsze niż tydzien
         */
        LocalDate oneWeekAgo = LocalDate.now().minusWeeks(1);

        return entityManager.createQuery(
                        "SELECT h FROM HealthMetrics h WHERE h.date > :oneWeekAgo",
                        HealthMetrics.class
                ).setParameter("oneWeekAgo", oneWeekAgo)
                .getResultList();
    }

    public List<HealthMetrics> findByWeightLess(double weight){
        /**
        Zapytanie o wage mniejszą od danej wagi
         */
        return  entityManager.createQuery(
                "SELECT h FROM HealthMetrics h WHERE h.weight < :weight",
                HealthMetrics.class
        ).setParameter("weight", weight)
                .getResultList();
    }
}
