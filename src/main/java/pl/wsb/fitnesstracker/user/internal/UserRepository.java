package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Objects;
import java.util.Optional;

/**
 * JPA Repository dla encji User.
 * Musi być PUBLIC, aby scheduler z innego pakietu mógł z niego korzystać.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Wyszukuje użytkownika po adresie email.
     * Implementacja zgodna z Twoim poprzednim kodem.
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

}