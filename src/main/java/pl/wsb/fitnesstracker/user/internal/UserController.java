package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.util.List;

/**
 * REST Controller dla API użytkowników.
 * <p>
 * Mapuje HTTP żądania na operacje biznesowe obsługiwane przez UserServiceImpl.
 * Base path: /v1/users
 * </p>
 * <p>
 * Obsługuje operacje CRUD na użytkownikach oraz wyszukiwanie po email i dacie urodzenia.
 * </p>
 *
 * @author Fitness Tracker Team
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    // Test: shouldReturnAllUsers_whenGettingAllUsers
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers().stream()
                .map(userMapper::toDto)
                .toList();
    }

    // Test: shouldReturnAllSimpleUsers_whenGettingAllUsers
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.findAllUsers().stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    // Test: shouldReturnDetailsAboutUser_whenGettingUserById
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    // Test: shouldReturnDetailsAboutUser_whenGettingUserByEmail
    @GetMapping("/email")
    public List<UserEmailSimpleDto> getUserByEmail(@RequestParam String email) {
        // Test oczekuje tablicy (jsonPath("$", hasSize(1))), więc pakujemy wynik w listę
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toEmailSimpleDto)
                .toList();
    }

    // Test: shouldReturnAllUsersOlderThan_whenGettingAllUsersOlderThan
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.findAllUsersOlderThan(time).stream()
                .map(userMapper::toDto)
                .toList();
    }

    // Test: shouldPersistUser_whenCreatingUser
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userService.createUser(user));
    }

    // Test: shouldRemoveUserFromRepository_whenDeletingClient
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    // Test: shouldUpdateUser_whenUpdatingUser
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User user = userService.getUser(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userMapper.updateUserFromDto(user, userDto);
        return userMapper.toDto(userService.updateUser(user));
    }
}
