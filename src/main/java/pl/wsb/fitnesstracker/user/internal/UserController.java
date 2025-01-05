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
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    UserController(UserServiceImpl userService, UserProvider userProvider, UserMapper userMapper) {
        this.userService = userService;
        this.userProvider = userProvider;
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
        return userProvider.getAllUsers()
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
        return userProvider.getAllUsers()
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
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        return userProvider.getUserByEmail(email)
                .map(user -> ok(userMapper.toDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get user by ID
     *
     * @param userId Long
     * @return UserDto
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserByUserId(@PathVariable Long userId) {
        return userProvider.getUser(userId)
                .map(user -> ok(userMapper.toDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get users born before a given date
     *
     * @param date LocalDate
     * @return List of UserDto
     */
    @GetMapping("/older/{date}")
    public List<UserDto> findUsersBornBefore(@PathVariable LocalDate date) {
        return userProvider.getUsersBornBefore(date).stream()
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

        System.out.println("User with ID: " + userId + "deleted.");

        userService.deleteUserById(userId);
    }

    /**
     * Update an existing user
     *
     * @param userId Long
     * @param userUpdateDto UserUpdateDto
     * @return User
     */
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto) {
        return userService.updateUser(userId, userUpdateDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}