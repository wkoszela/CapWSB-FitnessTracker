package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repozytorium dla encji {@link User}.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Szuka użytkownika na podstawie dokładnego adresu email.
     *
     * @param email adres email użytkownika
     * @return {@link Optional} zawierający użytkownika lub pusty
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    /**
     * Szuka użytkowników, których email zawiera podany fragment (ignorując wielkość
     * liter).
     *
     * @param emailFragment fragment adresu email
     * @return lista pasujących użytkowników
     */
    List<User> findByEmailContainingIgnoreCase(String emailFragment);

    /**
     * Szuka użytkowników urodzonych przed podaną datą.
     *
     * @param date data graniczna
     * @return lista użytkowników urodzonych przed podaną datą
     */
    List<User> findByBirthdateBefore(LocalDate date);

}