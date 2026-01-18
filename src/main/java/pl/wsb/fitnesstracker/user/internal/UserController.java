package pl.wsb.fitnesstracker.user.internal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserDtoNames;

/**
 * UserController is responsible for handling HTTP requests related to user
 * operations.
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
    @NonNull
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    @NonNull
    public List<UserDtoNames> getAllUsersNames() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDtoNames)
                .toList();
    }

}