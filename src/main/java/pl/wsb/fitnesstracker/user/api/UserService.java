package pl.wsb.fitnesstracker.user.api;

/**
 * Interfejs (API) dla operacji modyfikujących encje {@link User}.
 * Klasy implementujące są odpowiedzialne za wykonywanie zmian w ramach
 * transakcji bazodanowej.
 */
public interface UserService {

    /**
     * Tworzy nowego użytkownika.
     *
     * @param user użytkownik do utworzenia
     * @return utworzony użytkownik
     */
    User createUser(User user);

    /**
     * Usuwa użytkownika na podstawie jego ID.
     *
     * @param userId ID użytkownika do usunięcia
     */
    void deleteUser(Long userId);

    /**
     * Aktualizuje dane istniejącego użytkownika.
     *
     * @param user encja użytkownika z nowymi danymi
     * @return zaktualizowany użytkownik
     */
    User updateUser(User user);

}