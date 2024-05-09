package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsServiceImpl statisticsService;

    /**
     * Retrieves all statistics.
     *
     * @return  a list of Statistics objects representing all statistics
     */
    @GetMapping
    public List<Statistics> getAllStatistics() {
        return statisticsService.getAllStatistics();
    }

    /**
     * Retrieves statistics for a specific user.
     *
     * @param  userId  the ID of the user to retrieve statistics for
     * @return         a list of Statistics objects representing the user's statistics
     */
    @GetMapping("/user/{userId}")
    public List<Statistics> getStatisticsByUserId(@PathVariable Long userId) {
        return statisticsService.getStatisticsByUserId(userId);
    }

    /**
     * Retrieves statistics for a specific user.
     *
     * @param  email  the email address to search for in user emails
     * @return         a list of Statistics objects representing the user's statistics
     */
    @GetMapping("/user/email/{email}")
    public List<Statistics> getStatisticsByUserEmail(@PathVariable String email) {
        return statisticsService.getStatisticsByUserEmail(email);
    }

    /**
     * Retrieves statistics with an user age greater than or equal to the specified minimum age.
     *
     * @param  minAge  the minimum age of the users to search for
     * @return         a list of statistics with an user age greater than or equal to the specified minimum age
     */
    @GetMapping("/user/min-age/{minAge}")
    public List<Statistics> getStatisticsByMinAge(@PathVariable int minAge) {
        return statisticsService.getStatisticsByMinAge(minAge);
    }

    /**
     * Deletes a statistics entry by its ID.
     *
     * @param  statisticsId  the ID of the statistics entry to delete
     * @return              true if the statistics entry was successfully deleted, false otherwise
     */
    @DeleteMapping("/{statisticsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteStatistics(@PathVariable Long statisticsId) {
        return statisticsService.deleteStatistics(statisticsId);
    }

    /**
     * Creates a new statistics entry.
     *
     * @param statistics statistics to be created
     * @return           the created statistics
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Statistics createStatistics(@RequestBody Statistics statistics) {
        return statisticsService.createStatistics(statistics);
    }

    /**
     * Updates an existing statistics entry.
     *
     * @param statistics statistics to be updated
     * @return           the updated statistics
     */
    @PutMapping("/{statisticsId}")
    @ResponseStatus(HttpStatus.OK)
    public Statistics updateStatistics(@RequestBody Statistics statistics, @PathVariable Long statisticsId) {
        if (!statistics.getId().equals(statisticsId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Statistics IDs do not match");
        }
        return statisticsService.updateStatistics(statistics);
    }
}
