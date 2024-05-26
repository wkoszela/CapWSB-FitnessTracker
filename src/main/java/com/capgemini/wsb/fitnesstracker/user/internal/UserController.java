package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final TrainingServiceImpl trainingService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/basic")
    public List<BasicUserDto> getAllBasicUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::basicToDto)
                .toList();
    }

    @GetMapping("/{userid}")
    public List<UserDto> getSingleUserById(@PathVariable Long userid) {
        return userService.getUser(userid)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        User user = userMapper.toEntity(userDto);
        return userService.createUser(user);
    }

    @DeleteMapping("/{userid}")
    public void deleteUserById(@PathVariable Long userid) {
        trainingService.deleteTrainingByUserId(userid);
        userService.deleteUser(userid);
    }

    @GetMapping("/basic/email")
    public List<BasicUserDto> getSingleUserByEmail(@RequestParam String email) {
        return userService.getUserByPartialEmail(email)
                .stream()
                .map(userMapper::basicToDto)
                .toList();
    }


}