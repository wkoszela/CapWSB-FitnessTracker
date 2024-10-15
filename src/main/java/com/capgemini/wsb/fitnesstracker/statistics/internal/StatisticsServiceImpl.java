package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsDto;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsProvider;
import com.capgemini.wsb.fitnesstracker.statistics.api.StatisticsService;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
class StatisticsServiceImpl implements StatisticsProvider, StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final UserProvider userProvider;
    private final TrainingProvider trainingProvider;
    private final StatisticsMapper statisticsMapper;

    @Override
    public List<StatisticsDto> reGenerateStatistics() {

        //Clear the database of existing statistics
        statisticsRepository.deleteAll();

        var users = userProvider.getAllUsersEntity();
        if (users.isEmpty()) {
            throw new NotFoundException("No users found");
        }

        //Generate new statistics for every user
        for (var user : users) {
            assert user.getId() != null;
            Statistics statistics = generateStatisticsForSpecifiedUser(user.getId());
            statisticsRepository.save(statistics);
        }

        return getAllStatistics();
    }

    @Override
    public List<StatisticsDto> getAllStatistics() {
        return statisticsRepository.findAll()
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }

    @Override
    public Statistics generateStatisticsForSpecifiedUser(Long userId) {
        var user = userProvider.getUserEntity(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        var trainings = trainingProvider.getTrainingsEntityByUserId(userId);

        int noOfTrainings = trainings.size();
        double totalDistance = 0;
        double caloriesBurned = 0;

        // Generate statistics for every training assigned to a user
        for (var training : trainings) {
            totalDistance += training.getDistance();
            caloriesBurned += training.getActivityType().getCaloriesPerUnitOfDistance() * training.getDistance();
        }

        return new Statistics(user, noOfTrainings, totalDistance, (int)caloriesBurned);
    }

    @Override
    public StatisticsDto reGenerateStatisticsForSpecifiedUser(Long userId) {
        var user = userProvider.getUserEntity(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        var oldStatistics = statisticsRepository.findById(userId);

        oldStatistics.ifPresent(statisticsRepository::delete);

        var newStatistics = generateStatisticsForSpecifiedUser(userId);

        statisticsRepository.save(newStatistics);

        return statisticsMapper.toDto(newStatistics);
    }
}
