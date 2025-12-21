package pl.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

/**
 * Interfejs (API) dla operacji pobierania danych użytkowników.
 */
public interface UserProvider {

    /**
     * Pobiera użytkownika na podstawie jego ID.
     * Jeśli użytkownik o podanym ID nie zostanie znaleziony, zwraca
     * {@link Optional#empty()}.
     *
     * @param userId ID szukanego użytkownika
     * @return {@link Optional} zawierający użytkownika lub pusty, jeśli nie
     *         znaleziono
     */
    Optional<User> getUser(Long userId);

    /**
     * Pobiera użytkownika na podstawie jego adresu email.
     * Jeśli użytkownik o podanym adresie email nie zostanie znaleziony, zwraca
     * {@link Optional#empty()}.
     *
     * @param email adres email szukanego użytkownika
     * @return {@link Optional} zawierający użytkownika lub pusty, jeśli nie
     *         znaleziono
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Pobiera wszystkich użytkowników.
     *
     * @return lista wszystkich użytkowników
     */
    List<User> findAllUsers();

    /**
     * Znajduje użytkowników, których email zawiera podany fragment (ignorując
     * wielkość liter).
     *
     * @param emailFragment fragment adresu email do wyszukania
     * @return lista pasujących użytkowników
     */
    List<User> findUsersByEmailFragment(String emailFragment);

    /**
     * Znajduje użytkowników starszych niż określona data (urodzonych przed podaną
     * datą).
     *
     * @param time data graniczna
     * @return lista użytkowników urodzonych przed podaną datą
     */
    List<User> findUsersOlderThan(java.time.LocalDate time);

}