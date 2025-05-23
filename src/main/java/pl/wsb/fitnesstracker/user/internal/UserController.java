package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.List;

/**
 * REST controller for managing User resources.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream().map(userMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/email")
    public List<UserDto> searchByEmailFragment(@RequestParam String fragment) {
        return userService.findByPartialEmail(fragment)
                .stream().map(userMapper::toDto).toList();
    }

    @GetMapping("/search/older-than")
    public List<UserDto> findOlderThan(@RequestParam int age) {
        return userService.findOlderThan(age)
                .stream().map(userMapper::toDto).toList();
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userMapper.toDto(userService.createUser(userMapper.toEntity(userDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userMapper.toDto(
                userService.updateUser(id, userMapper.toEntity(userDto))
        ));
    }
}
