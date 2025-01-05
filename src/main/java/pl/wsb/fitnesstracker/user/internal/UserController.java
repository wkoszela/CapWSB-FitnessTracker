package pl.wsb.fitnesstracker.user.internal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller for users.
 */
@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final UserSimpleMapper userSimpleMapper;
    private final UserEmailSimpleMapper userEmailSimpleMapper;

    UserController(UserServiceImpl userService, UserMapper userMapper, UserSimpleMapper userSimpleMapper, UserEmailSimpleMapper userEmailSimpleMapper) {
        this.userService = userService;
        this.userSimpleMapper = userSimpleMapper;
        this.userEmailSimpleMapper = userEmailSimpleMapper;
        this.userMapper = userMapper;
    }

    /**
     * Get all users
     *
     * @return List of UserDto
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    /**
     * Get all users in simple format
     *
     * @return List of UserSimpleDto
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Get all users in detailed format
     *
     * @return List of UserSimpleDto
     */
    @GetMapping("/details")
    public List<UserDetailsDto> getAllDetailedUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::toDetailsDto)
                .toList();
    }

    /**
     * Get user by email
     *
     * @param email String
     * @return List of UserEmailSimpleDto
     */
    @GetMapping("/email")
    public List<UserEmailSimpleDto> getUserByEmail(@RequestParam String email) {
        return userService.getUsersByEmailPart(email)
                .stream()
                .map(userEmailSimpleMapper::toEmailSimpleDto)
                .toList();
    }

    /**
     * Get user by ID
     *
     * @param userId Long
     * @return UserDto
     */
    @GetMapping("/{userId}")
    public UserDto getUserByUserId(@PathVariable Long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " is not existing."));
    }

    /**
     * Get users born before a given date
     *
     * @param date LocalDate
     * @return List of UserDto
     */
    @GetMapping("/older/{date}")
    public List<UserDto> findUsersBornBefore(@PathVariable LocalDate date) {
        return userService.getUsersBornBefore(date).stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Add a new user
     *
     * @param userDto UserDto
     * @return User
     */
    @PostMapping
    @ResponseStatus(CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        System.out.println("User with e-mail: " + userDto.email() + "passed to the request.");

        return userService.createUser(userMapper.toEntity(userDto));
    }

    /**
     * Delete an existing user
     *
     * @param userId Long
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUserById(userId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot delete user with ID: " + userId + " with error: " + e.getMessage());
        }
    }

    /**
     * Update an existing user
     *
     * @param userId Long
     * @param userDto UserDto
     * @return User
     */
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        try {
            User foundUser = userService.getUser(userId).orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
            User updatedUser = userMapper.toUpdateEntity(userDto, foundUser);

            return userService.updateUser(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot update user with ID: " + userId + " with error: " + e.getMessage());
        }
    }

}