package pl.wsb.fitnesstracker.statistics.internal;

import pl.wsb.fitnesstracker.statistics.api.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Implementation of {@link StatisticsService}.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService, StatisticsProvider {
    private final StatisticsRepository statisticsRepository;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    /**
     * Returns statistics with the given id.
     *
     * @param statisticsId id of the statistics to be returned
     * @return statistics with the given id
     */
    @Override
    public Optional<Statistics> getStatistics(Long statisticsId) {
        return statisticsRepository.findById(statisticsId);
    }

    /**
     * Returns statistics for the given user.
     *
     * @param userId id of the user
     * @return statistics for the given user
     */
    @Override
    public Optional<Statistics> getStatisticsForUser(Long userId)
    {
        return statisticsRepository.findAll()
                .stream()
                .filter(statistic -> Objects.equals(statistic.getUser().getId(), userId))
                .findFirst();
    }

    @Override
    public List<Statistics> getMoreCaloriesBurned(Long burnedCalories) {
        return statisticsRepository.findByMoreBurnedCalories(burnedCalories);
    }

    /**
     * Returns all statistics.
     *
     * @return all statistics
     */
    @Override
    public List<Statistics> getAllStatistics()
    {
        return statisticsRepository.findAll();
    }

    /**
     * Creates a new statistics.
     *
     * @param statistics statistics to be created
     * @return created statistics
     */
    @Override
    public Statistics createStatistics(Statistics statistics) {
        Logger log = Logger.getLogger(StatisticsServiceImpl.class.getName());

        log.info("Creating statistics: " + statistics);
        if (statistics.getId() != null) {
            throw new IllegalArgumentException("Training id is already set");
        }

        return statisticsRepository.save(statistics);
    }

    /**
     * Updates an existing statistics entry.
     *
     * @param updatedStatistics the updated statistics
     * @return the updated statistics
     */
    @Override
    public Statistics updateStatistics(Statistics updatedStatistics) {
        return statisticsRepository.save(updatedStatistics);
    }

    /**
     * Deletes an existing statistics record.
     *
     * @param id the id of the statistics to delete
     */
    @Override
    public void deleteStatistics(Long id) {
        statisticsRepository.deleteById(id);
    }
}