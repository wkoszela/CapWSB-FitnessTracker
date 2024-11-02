package com.capgemini.wsb.fitnesstracker.user.api;

import java.util.Date;
import java.util.List;


/**
 * Interfejs serwisu do zarządzania operacjami na użytkownikach.
 */
public interface UserService {

    /**
     * Tworzy nowego użytkownika w systemie.
     *
     * @param user obiekt User zawierający dane użytkownika
     * @return utworzony User z przypisanym ID i zapisanymi danymi
     */
    User createUser(User user);

    /**
     * Wyszukuje użytkownika na podstawie jego unikalnego ID.
     *
     * @param id unikalny identyfikator użytkownika
     * @return User o określonym ID lub null, jeśli użytkownik nie został znaleziony
     */
    User findUserById(long id);

    /**
     * Wyszukuje użytkownika na podstawie jego adresu e-mail.
     *
     * @param email adres e-mail użytkownika
     * @return User o określonym adresie e-mail lub null, jeśli użytkownik nie został znaleziony
     */
    User findUserByEmail(String email);

    /**
     * Wyszukuje wszystkich użytkowników, którzy są starsi niż podany wiek.
     *
     * @param age wiek, z którym porównywani są użytkownicy
     * @return lista użytkowników, którzy są starsi niż określony wiek
     */
    List<User> findUsersOlderThan(int age);

    /**
     * Aktualizuje informacje istniejącego użytkownika.
     *
     * @param user obiekt User zawierający zaktualizowane dane
     * @return zaktualizowany User po zastosowaniu zmian
     */
    User updateUser(User user);
}
