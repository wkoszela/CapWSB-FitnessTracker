package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HealthMetricsRepository {


    @PersistenceContext
    private final EntityManager em;

    public HealthMetricsRepository(EntityManager em) {
        this.em = em;
    }
//    @Query(value ="select * from health_metrics where weight >100", nativeQuery = true)
//    List<HealthMetrics> a()

    public List<HealthMetrics> findHealthMetricsAboveDefinedWeight(double weight) {


        return em.createQuery("SELECT m FROM HealthMetrics m WHERE m.weight > :weight", HealthMetrics.class).setParameter("weight", weight).getResultList();

    }



}


