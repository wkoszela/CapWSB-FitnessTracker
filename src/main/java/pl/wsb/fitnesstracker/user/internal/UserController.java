package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.UserBasicDto;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * REST controller that exposes CRUD operations for {@code User} resources.
 *
 * <p>
 * Endpoints are versioned under {@code /v1/users}. The controller delegates
 * all business logic to {@link UserServiceImpl} and uses {@link UserMapper}
 * for conversions between entities and DTOs.
 * </p>
 *
 * <h3>HTTP Status Codes</h3>
 * <ul>
 * <li>{@code POST /v1/users} – {@link HttpStatus#CREATED 201}</li>
 * <li>{@code DELETE /v1/users/{id}} – {@link HttpStatus#NO_CONTENT 204}</li>
 * </ul>
 *
 * @author Your Name
 * @since 1.0
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    /**
     * Service layer that implements
     * {@link pl.wsb.fitnesstracker.user.api.UserService}.
     */
    private final UserServiceImpl userService;

    /**
     * Mapper that converts between {@link pl.wsb.fitnesstracker.user.api.User} and
     * DTOs.
     */
    private final UserMapper userMapper;

    /* POST */

    /**
     * Creates a new user.
     *
     * @param userDto payload containing the data of the user to create
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserDto userDto) {
        userService.createUser(userMapper.toEntity(userDto));
    }

    /* GET */

    /**
     * Searches users by a fragment of their e‑mail address.
     *
     * <p>
     * The search is case‑insensitive and returns only the ID and e‑mail of matching
     * users.
     * If no users match, an empty list is returned.
     * </p>
     *
     * @param fragment part of the e‑mail to search for
     * @return list of {@link UserEmailDto} containing ID and e‑mail of matching
     *         users
     */
    @GetMapping("/search/email")
    public List<UserEmailDto> searchUsersByEmail(@RequestParam String fragment) {
        String searchFragment = fragment.toLowerCase();

        return userService.findAllUsers().stream()
                .filter(u -> u.getEmail() != null && u.getEmail().toLowerCase().contains(searchFragment))
                .map(userMapper::toEmailDto)
                .toList();
    }

    /**
     * Returns users older than the specified age.
     *
     * <p>
     * The {@code age} parameter is interpreted in full years. The method calculates
     * the cutoff birthdate based on the current date and filters users born before
     * that date.
     * </p>
     *
     * @param age minimum age of users to include
     * @return list of {@link UserDto} containing full details of users older than
     *         the given age
     */
    @GetMapping("/olderThan")
    public List<UserDto> getUsersOlderThanAge(@RequestParam int age) {
        LocalDate cutoffDate = LocalDate.now().minusYears(age);
        return userService.getUsersOlderThan(cutoffDate).stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Returns a list of all users.
     *
     * @return {@link List} of {@link UserDto}
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Returns a lightweight list of users (only id, first and last name).
     *
     * @return {@link List} of {@link UserBasicDto}
     */
    @GetMapping("/simple")
    public List<UserBasicDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toBasicDto)
                .toList();
    }

    /**
     * Returns a single user identified by {@code id}.
     *
     * @param id primary key of the requested user
     * @return {@link UserDto} if found
     * @throws NoSuchElementException when no user with the given id exists
     */
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    /**
     * Looks up a user by e‑mail address.
     *
     * @param email e‑mail to search for
     * @return {@link List} containing the matching user or an empty list
     */
    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(user -> List.of(userMapper.toDto(user)))
                .orElse(List.of());
    }

    /**
     * Returns users born before the specified date.
     *
     * @param time cutoff birthdate
     * @return {@link List} of {@link UserDto}
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /* UPDATE */

    /**
     * Updates an existing user.
     *
     * @param userId  id of the user to update
     * @param userDto payload with updated data
     */
    @PutMapping("/{userId}")
    public void updateUser(
            @PathVariable Long userId,
            @RequestBody UserDto userDto) {

        var user = userService.getUser(userId).orElseThrow();

        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setBirthdate(userDto.birthdate());
        user.setEmail(userDto.email());

        userService.updateUser(user);
    }

    /* DELETE */

    /**
     * Deletes a user by id.
     *
     * @param userId id of the user to delete
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
}
