package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) dla operacji modyfikujących na encjach User poprzez API.
 * <p>
 * Definiuje publiczny interfejs serwisu do zarządzania użytkownikami.
 * Implementujące klasy są odpowiedzialne za wykonanie zmian w transakcji bazodanowej,
 * niezależnie od tego czy będzie to kontynuacja istniejącej transakcji czy nowa transakcja.
 * </p>
 *
 * @author Fitness Tracker Team
 * @see User
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);

}
