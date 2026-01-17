package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.wsb.fitnesstracker.user.api.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserBasicDto> getAllUsersBasic() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toBasicDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toDto)
                .map(List::of)
                .orElse(Collections.emptyList());
    }

    @GetMapping("/older/{time}")
    public List<UserDto> getAllUsersOlderThan(@PathVariable LocalDate time) {
        return userService.findAllUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(userMapper.toDto(createdUser));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.updateUser(id, user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/search/by-email")
    public List<UserEmailDto> findUsersByEmailFragment(@RequestParam String email) {
        return userService.findUsersByEmailFragment(email)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    @GetMapping("/by-name")
    public UserDto getUserByName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.getUserByFirstNameAndLastName(firstName, lastName)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(firstName, lastName));
    }
}
