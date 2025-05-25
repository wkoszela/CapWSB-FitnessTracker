package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.SimpleUserDto;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.List;

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

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return userMapper.toDto(userService.createUser(userMapper.toEntity(userDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/search/email")
    public List<UserDto> searchUsersByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/search/age")
    public List<UserDto> searchUsersByAgeGreaterThan(@RequestParam Integer age) {
        return userService.findUsersOlderThan(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userMapper.toDto(userService.updateUser(id, userMapper.toEntity(userDto)));
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.toDto(userService.findById(id));
    }

    @GetMapping("/simplified")
    public List<SimpleUserDto> getAllUsersSimplified() {
        return userService.findAllUsers().stream().map(userMapper::toSimpleDto).toList();
    }

    @GetMapping("/search")
    public List<UserDto> searchUsers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String birthDate,
            @RequestParam(required = false) String email) {
        return userService.searchUsers(id, fullName, birthDate, email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}