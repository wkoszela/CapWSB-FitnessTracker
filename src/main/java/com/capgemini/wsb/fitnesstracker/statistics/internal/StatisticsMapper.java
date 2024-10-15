package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingInputDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
public class StatisticsMapper {

    StatisticsDto toDto(Statistics statistics) {
        return new StatisticsDto(statistics.getId(),
                statistics.getUser(),
                statistics.getTotalTrainings(),
                statistics.getTotalDistance(),
                statistics.getTotalCaloriesBurned());
    }

    Statistics toEntity(StatisticsDto statisticsDto) {
        return new Statistics(
                statisticsDto.user(),
                statisticsDto.totalTrainings(),
                statisticsDto.totalDistance(),
                statisticsDto.totalCaloriesBurned());
    }

}
