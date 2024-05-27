package com.capgemini.wsb.fitnesstracker.user.internal;


import com.capgemini.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Management System")
class UserController {

    private final UserServiceImpl userService;
    private final TrainingServiceImpl trainingService;

    private final UserMapper userMapper;

    @Operation(summary = "View a list of all users")
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Operation(summary = "View a list of all user with basic information")
    @GetMapping("/basic")
    public List<BasicUserDto> getAllBasicUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::basicToDto)
                .toList();
    }

    @Operation(summary = "View specific user details")
    @GetMapping("/{userid}")
    public List<UserDto> getSingleUserById(@PathVariable Long userid) {
        return userService.getUser(userid)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Operation(summary = "Create new user")
    @PostMapping
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        User user = userMapper.toEntity(userDto);
        return userService.createUser(user);
    }

    @Operation(summary = "Delete specific user")
    @DeleteMapping("/{userid}")
    public void deleteUserById(@PathVariable Long userid) {
        trainingService.deleteTrainingByUserId(userid);
        userService.deleteUser(userid);
    }

    @Operation(summary = "View a list of all users with partial email")
    @GetMapping("/basic/")
    public List<BasicUserDto> getUsersByPartialEmail(@RequestParam String email) {
        return userService.getUserByPartialEmail(email)
                .stream()
                .map(userMapper::basicToDto)
                .toList();
    }

    @Operation(summary = "View a list of all users older than specific age")
    @GetMapping("/")
    public List<UserDto> getUserOlderThan(@RequestParam Integer age) {
        return userService.searchUsersByAgeGreaterThan(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Operation(summary = "Update an existing user")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }
}