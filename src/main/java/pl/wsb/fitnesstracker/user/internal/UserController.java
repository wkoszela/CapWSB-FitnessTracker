package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.wsb.fitnesstracker.user.api.UserBasicDto;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

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

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {

        var user = userMapper.toEntity(userDto);

        if (user == null) {

            // TODO: Throw an exception
            return null;
        }

        userService.createUser(user);
        return null;
    }

    @GetMapping
    public List<UserBasicDto> getAllUsers() {

        var userBasicDtos = userService.findAllUsers()
            .stream()
            .map(userMapper::toBasicDto)
            .toList();
        
        return userBasicDtos;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {

        var user = userService.getUser(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toDto(user);
    }

    @GetMapping("/by-email")
    public UserEmailDto getUserByEmail(@RequestParam String email) {
        var user = userService.getUserByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toEmailDto(user);
    }

    @GetMapping("/by-name")
    public List<UserDto> getUsersByName(@RequestParam String firstName) {
        return userService.getUsersByFirstName(firstName)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    // @PutMapping("/{id}")
    // public void updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {

    //     var user = userService.getUser(id)
    //         .orElseThrow(() -> new RuntimeException("User not found"));

    //     user.first
    // }


}