package pl.wsb.fitnesstracker.healthmetrics.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthmetricsRepository extends JpaRepository<Healthmetrics, Long> {
}
