package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

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

    @GetMapping
    public List<UserSumDTO> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSunDTO)
                .toList();
    }

    @PatchMapping("/{id}")
    void updateUser(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
        userService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    Optional<UserDto> getById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody UserDto dto) {
        userService.createUser(userMapper.fromCreate(dto));
    }


    @GetMapping("/search/email")
    List<UserByMailDTO> searchByEmail(@RequestParam String fragment) {
        return userService.getUserByEmail(fragment)
                .stream()
                .map(userMapper::toMailDTO)
                .toList();
    }

    @GetMapping("/search/age")
    List<UserDto> olderThan(@RequestParam int age) {
        return userService.getUserByAge(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}