package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {



    default List<User> findAllSummary() {
        return findAll().stream()
                .toList();
    }

    default Optional<User> findByID(Long id) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst();
    }
    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */

    default List<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail() != null)
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    default List<User> findOlderThan(LocalDate date) {
        return findAll().stream()
                .filter(user -> user.getBirthdate() != null)
                .filter(user ->
                        user.getBirthdate().isBefore(date))
                .toList();
    }

}
