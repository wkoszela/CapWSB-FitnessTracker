package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Spring Data JPA repository for {@link User} entities.
 *
 * <p>In addition to the standard CRUD methods inherited from
 * {@link JpaRepository}, custom query helpers are provided as default
 * methods that operate on the in‑memory stream of all users.  These are
 * suitable for small data sets or prototyping; in a production system
 * proper JPQL/Criteria queries should be used instead.</p>
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by its exact e‑mail address.
     *
     * @param email e‑mail to search for
     * @return {@link Optional} containing the user or empty if not found
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Returns all users with the given first name.
     *
     * @param firstName name to match
     * @return list of users with that first name
     */
    default List<User> findAllByFirstName(String firstName) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getFirstName(), firstName))
                .toList();
    }

    /**
     * Finds users whose birthdate is before the specified date.
     *
     * @param date cutoff date
     * @return list of users born before {@code date}
     */
    List<User> findAllByBirthdateBefore(LocalDate date);
}
