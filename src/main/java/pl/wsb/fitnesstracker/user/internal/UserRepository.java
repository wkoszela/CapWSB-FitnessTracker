package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing User persistence.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);

    default List<User> findByEmailContainingIgnoreCase(String fragment) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(fragment.toLowerCase()))
                .toList();
    }

    default List<User> findOlderThan(LocalDate ageThreshold) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(ageThreshold))
                .toList();
    }
}
