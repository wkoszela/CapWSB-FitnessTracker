package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
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

    @Override
    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll()
                .stream()
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
        for (var training : trainings) {
            totalDistance += training.getDistance();
            caloriesBurned += training.getActivityType().getCaloriesPerUnitOfDistance() * training.getDistance();
        }


        Statistics newStats = new Statistics(user, noOfTrainings, totalDistance, (int)caloriesBurned);

        return statisticsRepository.save(newStats);
    }
}
