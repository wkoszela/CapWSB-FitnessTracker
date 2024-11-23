package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsService, StatisticsProvider {

    private final StatisticsRepository statisticsRepository;
    private final UserProvider userProvider;

    /**
     * Creates new statistics.
     *
     * @param statistics statistics to be created
     * @return The created {@link Statistics}
     */
    @Override
    public Statistics createStatistics(Statistics statistics) {
        log.info("Creating Statistics: {}", statistics);
        if (statistics.getId() != null) {
            throw new IllegalArgumentException("Statistics already has an ID, cannot create!");
        }
        return statisticsRepository.save(statistics);
    }

    /**
     * Updates existing statistics.
     *
     * @param id                  ID of the statistics to update
     * @param statisticsUpdateDto DTO containing updated statistics data
     * @return The updated {@link Statistics}, or {@code null} if not found
     */
    @Override
    public Statistics updateStatistics(Long id, StatisticsUpdateDto statisticsUpdateDto) {
        Optional<Statistics> existingStatisticsOpt = statisticsRepository.findById(id);
        if (existingStatisticsOpt.isEmpty()) {
            return null;
        }
        Statistics existingStatistics = existingStatisticsOpt.get();

        // Update fields
        existingStatistics.setTotalTrainings(statisticsUpdateDto.totalTrainings());
        existingStatistics.setTotalDistance(statisticsUpdateDto.totalDistance());
        existingStatistics.setTotalCaloriesBurned(statisticsUpdateDto.totalCaloriesBurned());

        // Update user if provided
        if (statisticsUpdateDto.userId() != null) {
            Optional<User> userOpt = userProvider.getUser(statisticsUpdateDto.userId());
            if (userOpt.isEmpty()) {
                throw new IllegalArgumentException("User with ID=" + statisticsUpdateDto.userId() + " not found");
            }
            existingStatistics.setUser(userOpt.get());
        }

        log.info("Updating Statistics with ID {}: {}", id, existingStatistics);
        return statisticsRepository.save(existingStatistics);
    }

    /**
     * Deletes statistics by ID.
     *
     * @param id ID of the statistics to delete
     */
    @Override
    public void deleteStatistics(Long id) {
        log.info("Deleting Statistics with ID {}", id);
        statisticsRepository.deleteById(id);
    }

    /**
     * Retrieves statistics by ID.
     *
     * @param id ID of the statistics
     * @return An {@link Optional} containing the statistics if found, or {@link Optional#empty()} if not found
     */
    @Override
    public Optional<Statistics> getStatistics(Long id) {
        return statisticsRepository.findById(id);
    }

    /**
     * Retrieves statistics for a specific user by user ID.
     *
     * @param userId ID of the user
     * @return A list of statistics for the user
     */
    @Override
    public List<Statistics> getStatisticsByUserId(Long userId) {
        return statisticsRepository.findByUserId(userId);
    }

    /**
     * Retrieves all statistics where the total calories burned is greater than the specified value.
     *
     * @param minCalories the minimum number of calories
     * @return A list of statistics
     */
    @Override
    public List<Statistics> getStatisticsByMinCalories(int minCalories) {
        return statisticsRepository.findByTotalCaloriesBurnedGreaterThan(minCalories);
    }
}
