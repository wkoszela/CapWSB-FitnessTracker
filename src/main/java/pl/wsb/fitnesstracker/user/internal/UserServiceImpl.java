package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * Creates a new user and persists it in the database.
     *
     * <p>The user must not already have a database ID. If the ID is provided, an {@link IllegalArgumentException}
     * is thrown to prevent accidental updates using this method.</p>
     *
     * @param user the {@link User} entity to create
     * @return the persisted {@link User} entity
     * @throws IllegalArgumentException if the provided user has a non-null ID
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }


    /**
     * Retrieves a complete list of all users stored in the system.
     *
     * @return a {@link List} containing all {@link User} entities
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * <p>This method returns an {@link Optional} containing the user if found,
     * or {@link Optional#empty()} if no such user exists.</p>
     *
     * @param id the unique ID of the user
     * @return an {@link Optional} containing the matching {@link User}, or empty if not found
     */
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Retrieves a user by their exact email address.
     *
     * <p>This is useful for login or validation scenarios. Email matching is case-sensitive
     * unless configured otherwise in the repository implementation.</p>
     *
     * @param email the email address to search for
     * @return an {@link Optional} containing the matching {@link User}, or empty if not found
     */
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves a user by ID using the {@link UserProvider} interface.
     *
     * <p>This is an alias to {@link #findById(Long)} and exists to satisfy the {@link UserProvider} contract.</p>
     *
     * @param userId the unique user ID
     * @return an {@link Optional} containing the matching {@link User}, or empty if not found
     */
    @Override
    public Optional<User> getUser(Long userId) {
        return findById(userId);
    }

    /**
     * Searches for users matching the provided filtering criteria.
     *
     * <p>Any combination of first name, last name, and birthdate can be provided.
     * Null values are ignored. The search is case-insensitive for names and exact for birthdate.</p>
     *
     * @param firstName optional first name to match (case-insensitive)
     * @param lastName optional last name to match (case-insensitive)
     * @param birthdate optional birthdate to match exactly
     * @return a {@link List} of {@link User} entities that match the provided criteria
     */
    @Override
    public List<User> searchUsers(String firstName, String lastName, LocalDate birthdate) {
        return userRepository.findAll().stream()
                .filter(u -> firstName == null || u.getFirstName().equalsIgnoreCase(firstName))
                .filter(u -> lastName == null || u.getLastName().equalsIgnoreCase(lastName))
                .filter(u -> birthdate == null || u.getBirthdate().equals(birthdate))
                .toList();
    }

    /**
     * Deletes the user with the specified ID from the system.
     *
     * <p>If a user with the given ID exists, it is removed from the database and the deleted
     * user entity is returned. If no such user exists, a {@link UserNotFoundException} is thrown.</p>
     *
     * @param userId the ID of the user to delete
     * @return the deleted {@link User} entity
     * @throws UserNotFoundException if no user with the specified ID is found
     */
    @Override
    public User deleteUserById(Long userId) {
        User deletedUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.deleteById(userId);
        return deletedUser;
    }

    /**
     * Finds all users whose email addresses contain the specified fragment, ignoring case.
     *
     * <p>This method retrieves all users from the repository and performs in-memory filtering
     * using Java Streams. Only users with non-null email addresses that contain the specified
     * fragment (case-insensitive) are included in the result.</p>
     *
     * @param fragment the email substring to search for (case-insensitive)
     * @return a list of {@link User} objects whose email contains the specified fragment
     */
    public List<User> findUsersByEmailFragment(String fragment) {
        String lowerFragment = fragment.toLowerCase();

        return userRepository.findAll().stream()
                .filter(user -> user.getEmail() != null &&
                        user.getEmail().toLowerCase().contains(lowerFragment))
                .toList();
    }

    /**
     * Calculates the age in years based on the provided birthdate.
     *
     * @param birthdate the user's birthdate as a {@link LocalDate}
     * @return the age in full years
     * @throws IllegalArgumentException if the birthdate is null or in the future
     */
    public int getUserAge(LocalDate birthdate) {
        if (birthdate == null || birthdate.isAfter(LocalDate.now())) {
            return 0;
        }
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    /**
     * Retrieves all users whose age is greater than the specified threshold.
     *
     * <p>This method fetches all users from the repository and filters them based on their age,
     * calculated from their birthdate. Only users older than the given age threshold are included
     * in the result.</p>
     *
     * @param ageThreshold the minimum age (exclusive) users must exceed to be included
     * @return a list of {@link User} objects older than the specified age
     */
    @Override
    public List<User> findAllUsersOlderThan(int ageThreshold) {
        return userRepository.findAll().stream()
                .filter(user -> getUserAge(user.getBirthdate()) > ageThreshold).toList();
    }

    /**
     * Updates a specific attribute of a user identified by their ID.
     *
     * <p>This method performs a partial update on a user entity by setting the specified attribute
     * to the given value. Supported attributes include {@code "firstname"}, {@code "lastname"},
     * {@code "email"}, and {@code "birthdate"}. The update is case-insensitive with respect to the
     * attribute name.</p>
     *
     * <p>If the user with the specified ID does not exist, a {@link UserNotFoundException} is thrown.
     * If the attribute is unsupported or the value is invalid (e.g., incorrect date format), an
     * {@link IllegalArgumentException} is thrown.</p>
     *
     * @param userId   the ID of the user to update
     * @param attribute the name of the attribute to update
     * @param value     the new value for the specified attribute
     * @return the updated {@link User} entity
     * @throws UserNotFoundException if no user with the given ID is found
     * @throws IllegalArgumentException if the attribute is not supported or the value is invalid
     */
    @Override
    public User updateUserAttribute(Long userId, String attribute, String value) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        switch (attribute.toLowerCase()) {
            case "firstname" -> user.setFirstName(value);
            case "lastname" -> user.setLastName(value);
            case "email" -> user.setEmail(value);
            case "birthdate" -> {
                try {
                    user.setBirthdate(LocalDate.parse(value));
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid date format. Use yyyy-MM-dd.");
                }
            }
            default -> throw new IllegalArgumentException("Unsupported attribute: " + attribute);
        }

        return userRepository.save(user);
    }
}