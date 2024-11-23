package com.capgemini.wsb.fitnesstracker.statistics.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatisticsMapper {

    private final UserMapper userMapper;

    /**
     * Converts a {@link Statistics} entity to a {@link StatisticsDto}.
     *
     * @param statistics the statistics entity
     * @return the statistics DTO
     */
    public StatisticsDto toDto(Statistics statistics) {
        UserDto userDto = statistics.getUser() != null ? userMapper.toDto(statistics.getUser()) : null;
        return new StatisticsDto(
                statistics.getId(),
                statistics.getUser() != null ? statistics.getUser().getId() : null,
                userDto,
                statistics.getTotalTrainings(),
                statistics.getTotalDistance(),
                statistics.getTotalCaloriesBurned()
        );
    }

    /**
     * Converts a {@link StatisticsUpdateDto} to a {@link Statistics} entity.
     *
     * @param statisticsCreateDto the DTO containing data to create statistics
     * @param user                the user associated with the statistics
     * @return the statistics entity
     */
    public Statistics toEntity(StatisticsUpdateDto statisticsCreateDto, User user) {
        Statistics statistics = new Statistics();
        statistics.setUser(user);
        statistics.setTotalTrainings(statisticsCreateDto.totalTrainings());
        statistics.setTotalDistance(statisticsCreateDto.totalDistance());
        statistics.setTotalCaloriesBurned(statisticsCreateDto.totalCaloriesBurned());
        return statistics;
    }

    /**
     * Updates an existing {@link Statistics} entity with data from {@link StatisticsUpdateDto}.
     *
     * @param statisticsUpdateDto the DTO containing updated data
     * @param statistics           the existing statistics entity
     */
    public void updateEntity(StatisticsUpdateDto statisticsUpdateDto, Statistics statistics) {
        statistics.setTotalTrainings(statisticsUpdateDto.totalTrainings());
        statistics.setTotalDistance(statisticsUpdateDto.totalDistance());
        statistics.setTotalCaloriesBurned(statisticsUpdateDto.totalCaloriesBurned());
        // User update is handled in the service layer
    }
}
