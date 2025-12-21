// This code snippet is defining a Java interface named `StatisticsRepository` that extends
// `JpaRepository` interface provided by Spring Data JPA.
package pl.wsb.fitnesstracker.statistics.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
