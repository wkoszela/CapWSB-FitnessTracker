package pl.wsb.fitnesstracker.user.api;

import lombok.RequiredArgsConstructor;
import pl.wsb.fitnesstracker.user.internal.UserMapper;
import pl.wsb.fitnesstracker.user.internal.UserServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public List<SimpleUserDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<UserDto> getUserDetailsById(@PathVariable Long id) {
        return userService.getUser(id)
                .stream()
                .map(userMapper::toDto)
                .findFirst();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @GetMapping("/email")
    public List<UserDto> getUserDetailsByEmail(@RequestParam(required = false) String email) {
        return userService.getUsersByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return userService.createUser(userMapper.toEntity(userDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser(@RequestBody UserDto userDto, @PathVariable Long id) throws InterruptedException {
        return userService.updateUser(userMapper.toEntity(userDto), id);
    }

}