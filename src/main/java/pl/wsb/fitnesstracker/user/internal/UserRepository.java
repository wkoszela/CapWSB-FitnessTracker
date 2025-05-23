package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.Optional;

/**
 * Repository for managing User persistence.
 */
interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);

    List<User> findAll(); // already included in JpaRepository

    default List<User> findByEmailContainingIgnoreCase(String partialEmail) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(partialEmail.toLowerCase()))
                .toList();
    }

    default List<User> findOlderThan(int age) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().plusYears(age).isBefore(java.time.LocalDate.now()))
                .toList();
    }
}
