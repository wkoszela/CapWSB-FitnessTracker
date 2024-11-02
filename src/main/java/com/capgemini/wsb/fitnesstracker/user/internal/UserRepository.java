package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
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
     * Query searching users by email address. It matches by a case-insensitive partial match.
     *
     * @param emailFragment email fragment to search by
     * @return {@link List} containing found users
     */
    default List<User> findByEmailPartialMatch(String emailFragment) {
        return findAll().stream()
                        .filter(user -> user.getEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                        .toList();
    }

    /**
     * Query searching users by birthdate. It matches all users born before the threshold date.
     *
     * @param date the birthdate threshold
     * @return {@link List} containing found users
     */
    default List<User> findByBirthDateBefore(LocalDate date) {
        return findAll().stream()
                        .filter(user -> user.getBirthdate().isBefore(date))
                        .toList();
    }
}
