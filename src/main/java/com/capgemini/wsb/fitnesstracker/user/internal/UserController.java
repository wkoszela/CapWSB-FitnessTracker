package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Pobiera listę wszystkich użytkowników z pełnymi informacjami.
     *
     * @return lista UserDto zawierająca dane wszystkich użytkowników
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    /**
     * Pobiera listę wszystkich użytkowników z podstawowymi informacjami (ID, imię, nazwisko).
     *
     * @return lista UserSimpleDto zawierająca podstawowe dane wszystkich użytkowników
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllBasicInformationAboutUsers() {
        System.out.println("Listing users");
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }
    /**
     * Pobiera dane użytkownika na podstawie jego unikalnego ID.
     *
     * @param id unikalny identyfikator użytkownika
     * @return UserDto zawierający dane użytkownika
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) {
        return userMapper.toDto(userService.findUserById(id));
    }

    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.findUsersByEmailFragment(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    /**
     * Dodaje nowego użytkownika na podstawie przesłanych danych w formie UserDto.
     *
     * @param userDto dane nowego użytkownika
     * @return UserDto zawierający dane utworzonego użytkownika
     * @throws InterruptedException jeśli wystąpi błąd podczas tworzenia użytkownika
     */
    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        System.out.println("User with e-mail: " + userDto.email() + " passed to the request");
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userService.createUser(user));
    }
    /**
     * Usuwa użytkownika na podstawie jego unikalnego ID.
     *
     * @param id unikalny identyfikator użytkownika do usunięcia
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }


    @GetMapping("/older/{time}")
    public List<UserDto> findUsersOlderThan(@PathVariable("time") LocalDate time) {
//        logger.debug("Received request to find users older than: {}", time);
        System.out.println("Received request to find users older than:" + time);
        return userService.findUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        try {
            User existingUser = userService
                    .getUser(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User ID: " + userId + " not found"));
            User updatedUser = userMapper.toUpdateUser(userDto, existingUser);
            return userService.updateUser(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error on ID: " + userId + " Error code: " + e.getMessage());
        }
    }
}