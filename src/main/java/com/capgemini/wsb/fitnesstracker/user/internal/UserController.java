package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserSimpleDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toSimpleDto)
                          .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id)
                          .map(userMapper::toDto)
                          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto) {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        return userService.createUser(userMapper.toEntity(userDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        User user = userService.getUser(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
        userService.deleteUser(user);
    }

    @GetMapping("/email/{email}")
    public List<UserEmailDto> getUsersByEmail(@PathVariable String email) {
        return userService.findUsersByEmail(email)
                          .stream()
                          .map(userMapper::toEmailDto)
                          .toList();
    }

    @GetMapping("/minAge/{minAge}")
    public List<UserSimpleDto> getUsersByMinAge(@PathVariable int minAge) {
        return userService.findUsersByMinAge(minAge)
                          .stream()
                          .map(userMapper::toSimpleDto)
                          .toList();
    }

    @PostMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.getUser(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
        return userService.updateUser(userMapper.toEntityWithId(userDto, id));
    }
}