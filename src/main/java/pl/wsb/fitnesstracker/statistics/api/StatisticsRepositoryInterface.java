package pl.wsb.fitnesstracker.statistics.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepositoryInterface extends JpaRepository<Statistics, Long> {
}
