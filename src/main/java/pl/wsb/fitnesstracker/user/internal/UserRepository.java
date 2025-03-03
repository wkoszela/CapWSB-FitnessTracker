package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches all emails that contain the given part.
     * The search is case-insensitive.
     *
     * @param emailPart Part of the user email to match
     * @return {@link List} containing matched users
     */
    default List<User> findByEmailContains(String emailPart) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(emailPart.toLowerCase()))
                .collect(toList());
    }

    /**
     * Query searching users with birthdate older than the provided time.
     *
     * @param time Time used for matching users
     * @return {@link List} containing matched users
     */
    default List<User> findByBirthdateOlderThan(LocalDate time) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .collect(toList());
    }
}
