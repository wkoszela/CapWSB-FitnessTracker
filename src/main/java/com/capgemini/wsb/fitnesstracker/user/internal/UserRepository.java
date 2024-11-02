package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.time.LocalDate;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if
     *         none matched
     */
    default List<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail()
                        .toLowerCase()
                        .contains(email.toLowerCase()))
                .toList();
    }

    /**
     * Query searching users older than given birth date.
     *
     * @param birthDate birth date to compare
     * @return list of users older than given birth date
     */
    default List<User> findOlderThan(String birthDate) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(LocalDate.parse(birthDate)))
                .toList();
    }

}
