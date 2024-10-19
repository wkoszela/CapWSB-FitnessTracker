package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import org.springframework.stereotype.Component;

@Component
class StatisticsMapper {

    StatisticsDto toDto(Statistics statistics) {
        return new StatisticsDto(statistics.getId(),
                statistics.getUser(),
                statistics.getTotalTrainings(),
                statistics.getTotalDistance(),
                statistics.getTotalCaloriesBurned());
    }

    Statistics toEntity(StatisticsDto statisticsDto) {
        return new Statistics(
                statisticsDto.getUser(),
                statisticsDto.getTotalTrainings(),
                statisticsDto.getTotalDistance(),
                statisticsDto.getTotalCaloriesBurned());
    }

}
