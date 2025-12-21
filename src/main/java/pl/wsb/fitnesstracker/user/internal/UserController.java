package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Kontroler REST obsługujący operacje na użytkownikach.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Pobiera listę wszystkich użytkowników.
     *
     * @return lista wszystkich użytkowników jako DTO
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Pobiera uproszczoną listę wszystkich użytkowników.
     *
     * @return uproszczona lista użytkowników jako DTO
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllBasicUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Pobiera dane konkretnego użytkownika na podstawie ID.
     *
     * @param userId ID użytkownika
     * @return dane użytkownika jako DTO
     */
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    /**
     * Dodaje nowego użytkownika.
     *
     * @param userDto dane nowego użytkownika
     * @return utworzony użytkownik jako DTO
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userService.createUser(user));
    }

    /**
     * Usuwa użytkownika na podstawie ID.
     *
     * @param userId ID użytkownika
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * Wyszukuje użytkowników na podstawie fragmentu adresu email.
     *
     * @param email fragment adresu email
     * @return lista pasujących użytkowników jako DTO (email)
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUsersByEmailFragment(@RequestParam String email) {
        return userService.findUsersByEmailFragment(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    /**
     * Wyszukuje użytkowników starszych niż podana data.
     *
     * @param time data graniczna
     * @return lista pasujących użytkowników jako DTO
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.findUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Aktualizuje dane istniejącego użytkownika.
     *
     * @param userId  ID użytkownika do aktualizacji
     * @param userDto nowe dane użytkownika
     * @return zaktualizowany użytkownik jako DTO
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User user = userService.getUser(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setBirthdate(userDto.birthdate());
        user.setEmail(userDto.email());

        return userMapper.toDto(userService.updateUser(user));
    }
}