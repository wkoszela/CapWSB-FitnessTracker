package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.*;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
@Slf4j
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StatisticsProvider statisticsProvider;
    private final StatisticsMapper statisticsMapper;
    private final UserProvider userProvider;

    /**
     * Creates new statistics.
     *
     * @param statisticsCreateDto DTO containing statistics data
     * @return The created statistics DTO
     */
    @PostMapping
    public ResponseEntity<StatisticsDto> createStatistics(@RequestBody StatisticsUpdateDto statisticsCreateDto) {
        if (statisticsCreateDto.userId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<User> userOpt = userProvider.getUser(statisticsCreateDto.userId());

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        User user = userOpt.get();

        Statistics statistics = statisticsMapper.toEntity(statisticsCreateDto, user);
        Statistics createdStatistics = statisticsService.createStatistics(statistics);
        StatisticsDto statisticsDto = statisticsMapper.toDto(createdStatistics);

        return ResponseEntity.status(201).body(statisticsDto);
    }

    /**
     * Updates existing statistics.
     *
     * @param id                   ID of the statistics to update
     * @param statisticsUpdateDto DTO containing updated statistics data
     * @return The updated statistics DTO, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<StatisticsDto> updateStatistics(
            @PathVariable Long id,
            @RequestBody StatisticsUpdateDto statisticsUpdateDto) {

        Statistics updatedStatistics = statisticsService.updateStatistics(id, statisticsUpdateDto);
        if (updatedStatistics == null) {
            return ResponseEntity.notFound().build();
        }

        StatisticsDto statisticsDto = statisticsMapper.toDto(updatedStatistics);
        return ResponseEntity.ok(statisticsDto);
    }

    /**
     * Retrieves statistics by ID.
     *
     * @param id ID of the statistics
     * @return The statistics DTO, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<StatisticsDto> getStatisticsById(@PathVariable Long id) {
        Optional<Statistics> statisticsOpt = statisticsProvider.getStatistics(id);
        if (statisticsOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        StatisticsDto statisticsDto = statisticsMapper.toDto(statisticsOpt.get());
        return ResponseEntity.ok(statisticsDto);
    }

    /**
     * Retrieves statistics for a specific user by user ID.
     *
     * @param userId ID of the user
     * @return A list of statistics DTOs for the user
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StatisticsDto>> getStatisticsByUserId(@PathVariable Long userId) {
        List<Statistics> statisticsList = statisticsProvider.getStatisticsByUserId(userId);
        List<StatisticsDto> statisticsDtos = statisticsList.stream()
                .map(statisticsMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(statisticsDtos);
    }

    /**
     * Retrieves all statistics where the total calories burned is greater than the specified value.
     *
     * @param minCalories the minimum number of calories
     * @return A list of statistics DTOs
     */
    @GetMapping("/calories/{minCalories}")
    public ResponseEntity<List<StatisticsDto>> getStatisticsByMinCalories(@PathVariable int minCalories) {
        List<Statistics> statisticsList = statisticsProvider.getStatisticsByMinCalories(minCalories);
        List<StatisticsDto> statisticsDtos = statisticsList.stream()
                .map(statisticsMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(statisticsDtos);
    }

    /**
     * Deletes statistics by ID.
     *
     * @param id ID of the statistics to delete
     * @return 204 No Content if deleted successfully, or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistics(@PathVariable Long id) {
        Optional<Statistics> statisticsOpt = statisticsProvider.getStatistics(id);
        if (statisticsOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        statisticsService.deleteStatistics(id);
        return ResponseEntity.noContent().build();
    }
}
