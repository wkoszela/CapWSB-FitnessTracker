package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     * (Ta metoda już istniała - zostawiamy ją)
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if
     *         none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    /**
     * Szuka użytkowników, których email zawiera podany fragment (ignorując wielkość
     * liter).
     * Realizuje wymóg: "wyszukiwanie po fragmencie nazwy, bez rozróżniania
     * wielkości liter".
     */
    List<User> findByEmailContainingIgnoreCase(String emailFragment);

    /**
     * Szuka użytkowników urodzonych przed podaną datą.
     * Służy do znajdowania osób starszych niż X lat.
     */
    List<User> findByBirthdateBefore(LocalDate date);

}