package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.BasicDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserAddDto;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<BasicDto> getAllBasicUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toBasicDto)  // Changed from toSimpleDto to toBasicDto
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserAddDto userAddDto) {
        User user = userMapper.toEntity(userAddDto);
        User createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }


    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
    }

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found"));
    }

    @GetMapping("/search")
    public UserDto getUserByName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.getUserByFirstNameAndLastName(firstName, lastName)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with name " + firstName + " " + lastName + " not found"));
    }



}

