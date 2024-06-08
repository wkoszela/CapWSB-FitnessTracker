package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import io.micrometer.core.instrument.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistics, Long> {

    void deleteById(Long statisticId);
}
