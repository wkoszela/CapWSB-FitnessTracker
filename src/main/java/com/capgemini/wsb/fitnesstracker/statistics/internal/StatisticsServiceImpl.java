package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsServiceImpl implements StatisticsProvider, StatisticsService {

    private final StatisticsRepository statisticsRepository;

    /**
     * Retrieves the statistics with the given ID.
     *
     * @param  statisticsId  the ID of the statistics to retrieve
     * @return               the optional statistics object, which may be empty if not found
     */
    @Override
    public Optional<Statistics> getStatistics(Long statisticsId) {
        return statisticsRepository.findById(statisticsId);
    }

    /**
     * Retrieves all statistics from the statistics repository.
     *
     * @return a list of Statistics objects representing all statistics
     */
    @Override
    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }

    /**
     * Retrieves a list of statistics associated with the given user ID.
     *
     * @param  userId  the ID of the user
     * @return         a list of statistics associated with the user
     */
    @Override
    public List<Statistics> getStatisticsByUserId(Long userId) {
        return statisticsRepository.findAllByUserId(userId);
    }

    /**
     * Retrieves a list of statistics associated with the given user email.
     *
     * @param  email  the email address to search for in user emails
     * @return        a list of Statistics objects representing the user's statistics
     */
    @Override
    public List<Statistics> getStatisticsByUserEmail(String email) {
        return statisticsRepository.findAllByUserEmail(email);
    }

    /**
     * Retrieves a list of statistics with an user age greater than or equal to the specified minimum age.
     *
     * @param  minAge  the minimum age of the users to search for
     * @return         a list of statistics with an user age greater than or equal to the specified minimum age
     */
    @Override
    public List<Statistics> getStatisticsByMinAge(int minAge) {
        return statisticsRepository.findAllByMinAge(minAge);
    }

    /**
     * Deletes a statistics entry by its ID.
     *
     * @param  statisticsId  the ID of the statistics entry to delete
     * @return               true if the statistics entry was successfully deleted, false otherwise
     */
    @Override
    public boolean deleteStatistics(Long statisticsId) {
        Optional<Statistics> statistics = getStatistics(statisticsId);
        if (statistics.isPresent()) {
            statisticsRepository.delete(statistics.get());
            return true;
        }
        return false;
    }

    /**
     * Creates a new statistics entry.
     *
     * @param statistics statistics to be created
     * @return           the created statistics
     */
    @Override
    public Statistics createStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }

    /**
     * Updates an existing statistics entry.
     *
     * @param statistics statistics to be updated
     * @return           the updated statistics
     */
    @Override
    public Statistics updateStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }
}
