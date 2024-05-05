package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    /**
     * Query searching users by email address in a case-insensitive manner.
     *
     * @param  email email to search for in user emails
     * @return          list of users whose email contains the specified email (case-insensitive)
     */
    default List<User> findAllByEmail(String email) {
        // part of email and insensitive
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    /**
     * Finds and returns a list of users with an age greater than or equal to the specified minimum age.
     *
     * @param  minAge  the minimum age of the users to search for
     * @return        a list of users whose age is greater than or equal to the specified minimum age
     */
    default List<User> findAllByMinAge(int minAge) {
        int currentYear = java.time.LocalDate.now().getYear();
        return findAll().stream()
                .filter(user -> currentYear - user.getBirthdate().getYear() >= minAge)
                .toList();
    }
}
