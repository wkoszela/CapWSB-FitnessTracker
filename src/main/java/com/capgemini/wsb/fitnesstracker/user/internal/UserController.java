package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

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

    /**
     * Wyszukuje użytkowników starszych niż podany wiek.
     *
     * @param age minimalny wiek użytkowników do wyszukania
     * @return lista UserDto zawierająca dane użytkowników starszych niż podany wiek
     */
    @GetMapping("/search/age")
    public List<UserDto> findUsersOlderThan(@RequestParam int age) {
        return userService.findUsersOlderThan(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}