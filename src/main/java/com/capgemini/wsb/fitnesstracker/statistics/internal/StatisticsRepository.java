package com.capgemini.wsb.fitnesstracker.statistics.internal;

import com.capgemini.wsb.fitnesstracker.statistics.api.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    /**
     * Finds all statistics by user ID.
     *
     * @param  userId  the ID of the user
     * @return         a list of statistics associated with the user ID
     */
    default List<Statistics> findAllByUserId(Long userId) {
        return findAll().stream()
                .filter(statistics -> Objects.equals(statistics.getUser().getId(), userId))
                .toList();
    }

    /**
     * Finds all statistics by user email address in a case-insensitive manner.
     *
     * @param  email email to search for in user emails
     * @return          list of statistics whose user email contains the specified email (case-insensitive)
     */
    default List<Statistics> findAllByUserEmail(String email) {
        return findAll().stream()
                .filter(statistics -> statistics.getUser().getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    /**
     * Finds and returns a list of statistics with an user age greater than or equal to the specified minimum age.
     *
     * @param  minAge  the minimum age of the users to search for
     * @return         a list of statistics with an user age greater than or equal to the specified minimum age
     */
    default List<Statistics> findAllByMinAge(int minAge) {
        int currentYear = java.time.LocalDate.now().getYear();
        return findAll().stream()
                .filter(statistics -> currentYear - statistics.getUser().getBirthdate().getYear() >= minAge)
                .toList();
    }
}