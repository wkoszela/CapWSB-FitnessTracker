package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.time.LocalDate;

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
     * Retrieves the first user whose first and last names contain the specified strings (case-insensitive).
     *
     * @param firstName a substring of the user's first name to search for (case-insensitive)
     * @param lastName a substring of the user's last name to search for (case-insensitive)
     * @return an Optional containing the first User matching the criteria, or an empty Optional if no match is found
     */
    default Optional<User> getUserByNameSurname(String firstName, String lastName) {
        return findAll().stream()
                .filter(user -> user.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                .filter(user -> user.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .findFirst();
    }

    /**
     * Retrieves a list of users with a specific birthdate.
     *
     * @param birthday the birthdate to filter users by
     * @return a list of users who have the specified birthdate
     */
    default List<User> getUserByBirthday(LocalDate birthday) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getBirthdate(), birthday))
                .toList();
    }
}
