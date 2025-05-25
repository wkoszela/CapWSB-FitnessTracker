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

//    UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUser(Long userId) {
        return findById(userId);
    }

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