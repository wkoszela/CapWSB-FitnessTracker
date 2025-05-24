package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.SimpleUserDto;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

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

    /**
     * Zwraca listę podstawowych informacji o wszystkich użytkownikach zapisanych w systemie.
     * Każdy obiekt w liście zawiera jedynie identyfikator użytkownika oraz jego nazwę (np. imię i nazwisko lub login).
     * Endpoint służy do prezentacji uproszczonych danych użytkowników, bez ujawniania danych wrażliwych.
     * @return lista użytkowników w postaci obiektów {@link SimpleUserDto}, zawierających tylko ID i nazwę użytkownika
     */
    @GetMapping("/simple")
    public List<SimpleUserDto> getSimpleUsers() {
        return userService.findAllUsers().stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }


    /**
     * Pobiera szczegóły użytkownika na podstawie jego identyfikatora.
     * @param id identyfikator użytkownika
     * @return obiekt {@link UserDto} jeśli użytkownik istnieje, w przeciwnym razie kod odpowiedzi 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Wyszukuje użytkowników zawierających podany fragment adresu e-mail (ignorując wielkość liter).
     * @param email fragment adresu e-mail
     * @return lista dopasowanych użytkowników w postaci {@link UserEmailDto} (tylko ID i adres e-mail), lub kod 404, jeśli brak wyników
     */
    @GetMapping("/email")
    public ResponseEntity<List<UserEmailDto>> getUserByEmail(@RequestParam String email) {
        var users = userService.findByPartialEmail(email).stream()
                .map(userMapper::toEmailDto)
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
     * Usuwa użytkownika o podanym identyfikatorze.
     * Sprawdza, czy użytkownik o danym ID istnieje, a jeśli tak, usuwa go wraz z powiązanymi danymi.
     * Jeśli użytkownik nie zostanie znaleziony, zwraca status 404 Not Found.
     *
     * @param id identyfikator użytkownika do usunięcia
     * @return {@code 204 No Content} jeśli użytkownik został pomyślnie usunięty,
     *         {@code 404 Not Found} jeśli użytkownik o podanym ID nie istnieje
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.getUser(id).isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
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
