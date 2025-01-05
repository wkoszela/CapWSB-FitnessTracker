package pl.wsb.fitnesstracker.statistics.internal;

import pl.wsb.fitnesstracker.statistics.api.Statistics;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for statistics.
 */
@RestController
@RequestMapping("/v1/statistics")
public class StatisticsController {
    private final StatisticsServiceImpl statisticsService;
    private final StatisticsMapper statisticsMapper;

    public StatisticsController(StatisticsServiceImpl statisticsService, StatisticsMapper statisticsMapper) {
        this.statisticsService = statisticsService;
        this.statisticsMapper = statisticsMapper;
    }

    /**
     * Retrieves a list of all statistics.
     *
     * @return a list of DTO representations of statistics
     */
    @GetMapping
    public List<StatisticsDto> getAllStatistics() {
        return statisticsService.getAllStatistics()
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a list of statistics for a specific user.
     *
     * @param userId the user ID to search for
     * @return a list of DTO representations of statistics
     */
    @GetMapping("/{userId}")
    public List<StatisticsDto> getStatisticsByUser(@PathVariable Long userId) {
        return statisticsService.getStatisticsForUser(userId)
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a list of statistics with more burned calories than the given value.
     *
     * @param burnedCalories the burned calories to search for
     * @return a list of DTO representations of statistics
     */
    @GetMapping("/moreCaloriesBurned")
    public List<StatisticsDto> getMoreCaloriesBurned(@RequestParam("burnedCalories") Long burnedCalories) {
        return statisticsService.getMoreCaloriesBurned(burnedCalories)
                .stream()
                .map(statisticsMapper::toDto)
                .toList();
    }

    /**
     * Adds a new statistics entry.
     *
     * @param statisticsDto the statistics DTO to add
     * @return the added statistics DTO
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatisticsDto addStatistics(@RequestBody StatisticsDtoWithUserId statisticsDto) {
        try {
            statisticsService.createStatistics(
                    statisticsMapper.toEntity(statisticsDto, null)
            );

        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot add statistics with error: " + e.getMessage());
        }

        return null;
    }

    /**
     * Updates an existing statistics entry.
     *
     * @param statisticsId the ID of the statistics to update
     * @param statisticsDto the updated statistics DTO
     * @return the updated statistics DTO
     */
    @PutMapping("/{statisticsId}")
    public Statistics updateStatistics(@PathVariable Long statisticsId, @RequestBody StatisticsDtoWithUserId statisticsDto) {
        try {
            Statistics foundStatistics = statisticsService.getStatistics(statisticsId).orElseThrow(() -> new IllegalArgumentException("Statistics with ID: " + statisticsId + " not found"));
            Statistics updatedStatistics = statisticsMapper.toUpdateEntity(statisticsDto, foundStatistics);

            return statisticsService.updateStatistics(updatedStatistics);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot update statistics with ID: " + statisticsId + " with error: " + e.getMessage());
        }
    }

    /**
     * Deletes an existing statistics entry.
     *
     * @param statisticsId the ID of the statistics to delete
     */
    @DeleteMapping("/{statisticsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStatistics(@PathVariable Long statisticsId) {
        try {
            statisticsService.deleteStatistics(statisticsId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot delete statistics with ID: " + statisticsId + " with error: " + e.getMessage());
        }
    }
}