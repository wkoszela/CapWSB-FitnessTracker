package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
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
     * Query searching users by email fragment, case-insensitive.
     *
     * @param emailFragment email fragment to search
     * @return list of users with matching emails
     */
    default List<User> findByEmailContainingIgnoreCase(String emailFragment) {
        String normalizedFragment = normalize(emailFragment);
        return findAll().stream()
                .filter(user -> normalize(user.getEmail()).contains(normalizedFragment))
                .toList();
    }

    /**
     * Query searching users born before provided date.
     *
     * @param date cutoff date
     * @return list of users born before the date
     */
    default List<User> findByBirthdateBefore(LocalDate date) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .toList();
    }

    private static String normalize(String value) {
        return value == null ? "" : value.toLowerCase(Locale.ROOT);
    }

}
