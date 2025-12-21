package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * zwraca dane wszystkich użytkowników
     * @return
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * update`uje usera zmieniając dane, które zostały wprowadzone
     * @param id - id uzytkownika
     * @param dto - klasa zawierajaca nowe dane
     */
    @PutMapping("/{id}")
    void updateUser(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
        userService.update(id, dto);
    }

    /**
     * usuwa uzytkownika po id
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    /**
     * szuka i pobiera dane uzytkownika po ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Optional<UserDto> getById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto);
    }

    /**
     * tworzy nowego uzytkownika w bazie
     * @param dto dane nowego uzytkownika
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody UserDto dto) {
        userService.createUser(userMapper.fromCreate(dto));
    }

    /**
     * szuka i pobiera dane uzytkownika po email lub jego fragmencie
     * @param email
     * @return
     */
    @GetMapping("/email")
    List<UserByMailDTO> searchByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toMailDTO)
                .toList();
    }

    /**
     * szuka i pobiera dane uzytkownikow starszych od wprowadzonej daty
     * @param date
     * @return
     */
    @GetMapping("/older/{date}")
    List<UserDto> olderThan(@PathVariable LocalDate date) {
        return userService.getUserByAge(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * zwraca uproszczone dane do id, imienia i nazwiska wszystkich użytkowników
     * @return
     */
    @GetMapping("/simple")
    List<UserSumDTO> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSumDTO)
                .toList();
    }
}