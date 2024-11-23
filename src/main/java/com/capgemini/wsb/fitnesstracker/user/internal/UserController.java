package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;
    private final UserSimpleMapper userSimpleMapper;
    private final UserEmailSimpleMapper userEmailSimpleMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userSimpleMapper::toSimpleDto)
                .toList();
    }
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
    }
    @GetMapping("/email")
    public List<UserEmailSimpleDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmailIgnoreCase(email)
                .stream()
                .map(userEmailSimpleMapper::toEmailSimpleDto)
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
        try {
            User user = userMapper.toEntity(userDto);
            userService.createUser(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot add user with email: " + userDto.email() + " with error: " + e.getMessage());
        }
        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        // TODO: saveUser with Service and return User
        return null;
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        try {
            User foundUser = userService.getUser(userId).orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
            User updatedUser = userMapper.toUpdateEntity(userDto, foundUser);

            return userService.updateUser(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot update user with ID: " + userId + " with error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot delete user with ID: " + userId + " with error: " + e.getMessage());
        }
    }
}