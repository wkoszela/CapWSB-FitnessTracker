package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl {

    private final StatisticRepository statisticRepository;

    public void deleteStatistic(final Long statisticId) {
        Statistics statistics = statisticRepository.findById(statisticId)
                .orElseThrow(() -> new IllegalArgumentException("Statistic not found"));
        statisticRepository.delete(statistics);
    }
}
