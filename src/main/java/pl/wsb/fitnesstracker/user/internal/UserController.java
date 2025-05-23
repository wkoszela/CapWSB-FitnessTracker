package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User savedUser = userService.createUser(userMapper.toEntity(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(savedUser));
    }

    @GetMapping
    public List<UserDto> getAllUsersFull() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public ResponseEntity<List<UserDto>> getUserByEmailAsList(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toDto)
                .map(dto -> ResponseEntity.ok(List.of(dto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<UserDto> searchUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate
    ) {
        return userService.searchUsers(firstName, lastName, birthdate).stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Handles HTTP DELETE requests to remove a user by their ID.
     *
     * <p>Delegates the deletion process to the {@link UserService}. If the user with the specified ID
     * exists, they are deleted and the deleted user entity is returned in the response body.
     * If the user does not exist, the method catches the {@link UserNotFoundException} and
     * rethrows it as a {@link ResponseStatusException} with HTTP status 404 (Not Found).</p>
     *
     * @param userId the ID of the user to delete
     * @return a {@link ResponseEntity} containing the deleted {@link User} and HTTP status 200 (OK)
     * @throws ResponseStatusException with status 404 if no user with the specified ID is found
     */
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(userMapper.toDto(userService.deleteUserById(userId)));
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    /**
     * Handles HTTP GET requests to find users whose email addresses contain the specified fragment,
     * ignoring case.
     *
     * <p>The method accepts an email fragment as a request parameter and returns a list of users
     * matching that fragment. The response contains a list of {@link UserEmailDto}, which include
     * only the user ID and email address.</p>
     *
     * @param fragment the email substring to search for (case-insensitive)
     * @return a {@link ResponseEntity} containing a list of {@link UserEmailDto} and HTTP status 200 (OK)
     */
    @GetMapping("/email/fragment")
    public ResponseEntity<List<UserEmailDto>> findUsersByEmailFragment(@RequestParam String fragment) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findUsersByEmailFragment(fragment).stream()
                .map(userMapper::toEmailDto)
                .toList());
    }

    /**
     * Handles HTTP GET requests to retrieve all users older than the specified age threshold.
     *
     * <p>Delegates the filtering logic to the {@link UserService}, which returns a list of users
     * whose age is greater than the provided threshold.</p>
     *
     * @param ageThreshold the minimum age (exclusive) users must be older than
     * @return a {@link ResponseEntity} containing the list of {@link User} objects and HTTP status 200 (OK)
     */
    @GetMapping("/olderThan/{ageThreshold}")
    public ResponseEntity<List<UserDto>> findUsersOlderThan(@PathVariable int ageThreshold)
    {
        return ResponseEntity.status(HttpStatus.OK).
                body(userService.findAllUsersOlderThan(ageThreshold).stream().map(userMapper::toDto).toList());
    }

    /**
     * Handles HTTP PATCH requests to update a specific attribute of a user.
     *
     * <p>This method allows partial updates to a user by specifying the attribute name and the new value
     * as request parameters. It delegates the update logic to the {@link UserService}. If the user with
     * the given ID exists and the attribute is valid, the user's information is updated and the updated
     * {@link User} object is returned in the response body.</p>
     *
     * <p>If the specified user does not exist or if the attribute/value is invalid, an exception is
     * caught and rethrown as a {@link ResponseStatusException} with HTTP status 400 (Bad Request).</p>
     *
     * @param id        the ID of the user to update
     * @param attribute the name of the attribute to update (e.g., "firstName", "email")
     * @param value     the new value to set for the specified attribute
     * @return a {@link ResponseEntity} containing the updated {@link User} and HTTP status 200 (OK)
     * @throws ResponseStatusException with status 400 if the attribute or value is invalid,
     *                                  or if the user does not exist
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUserAttribute(
            @PathVariable Long id,
            @RequestParam String attribute,
            @RequestParam String value) {

        try {
            return ResponseEntity.ok(userMapper.toDto(userService.updateUserAttribute(id, attribute, value)));
        } catch (IllegalArgumentException | UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}