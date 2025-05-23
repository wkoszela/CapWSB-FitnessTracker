package pl.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

/**
 * Interfejs API do odczytu informacji o użytkownikach.
 * Umożliwia pobieranie pojedynczych lub wszystkich użytkowników.
 */
public interface UserProvider {

    Optional<User> getUser(Long userId);

    Optional<User> getUserByEmail(String email);

    List<User> findAllUsers();
}
