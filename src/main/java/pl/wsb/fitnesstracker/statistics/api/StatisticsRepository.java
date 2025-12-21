package pl.wsb.fitnesstracker.statistics.api;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repozytorium dla encji {@link Statistics}.
 */
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
