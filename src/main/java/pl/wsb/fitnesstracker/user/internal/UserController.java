package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.SimpleUserDto;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.util.List;

/**
 * REST API kontroler dla operacji na użytkownikach.
 * Umożliwia operacje CRUD i wyszukiwanie po emailu i wieku.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Zwraca wszystkich użytkowników.
     * @return lista użytkowników
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<SimpleUserDto> getSimpleUsers() {
        return userService.findAllUsers().stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }


    /**
     * Pobiera użytkownika po ID.
     * @param id identyfikator użytkownika
     * @return szczegóły użytkownika lub 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Wyszukuje użytkowników po fragmencie emaila (bez rozróżniania wielkości liter).
     * @param email fragment adres email
     * @return pasujący użytkownicy
     */
    @GetMapping("/email")
    public ResponseEntity<List<UserDto>> getUserByEmail(@RequestParam String email) {
        var users = userService.findByPartialEmail(email).stream()
                .map(userMapper::toDto)
                .toList();

        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }


    /**
     * Wyszukuje użytkowników starszych niż podany wiek.
     * @param date wiek minimalny
     * @return pasujący użytkownicy
     */
    @GetMapping("/older/{date}")
    public List<UserDto> findOlderThanDate(@PathVariable("date") LocalDate date) {
        return userService.findOlderThan(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Tworzy nowego użytkownika.
     * @param userDto dane nowego użytkownika
     * @return utworzony użytkownik
     */
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        var created = userService.createUser(userMapper.toEntity(userDto));
        return ResponseEntity.status(201).body(userMapper.toDto(created));
    }

    /**
     * Usuwa użytkownika po ID.
     * @param id identyfikator użytkownika
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Aktualizuje dane użytkownika.
     * @param id identyfikator użytkownika
     * @param userDto nowe dane użytkownika
     * @return zaktualizowany użytkownik
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        var updated = userService.updateUser(id, userMapper.toEntity(userDto));
        return ResponseEntity.ok(userMapper.toDto(updated));
    }
}
