package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    /**
     * Query for statistics of a user with specified id
     *
     * @param userId An id of a user whose statistics to query
     * @return {@link Optional} of statistics. Returns {@link Optional#empty} when no statistics found
     */
    Optional<Statistics> findByUserId(Long userId);
}
