package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    List<String> findByUserId(Long userId);
}
