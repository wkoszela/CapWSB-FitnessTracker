package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        // TODO: saveUser with Service and return User
        return null;
    }

    /**
     * This method returns a list of all users in a simplified form.
     * 
     * @return List of all users in a simplified form
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * This method returns a user with a given ID.
     * 
     * @param userId ID of the user to be returned
     * @return User with given ID
     */
    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    /**
     * This method returns a user with a given e-mail address.
     * 
     * @param email E-mail address of the user to be returned
     * @return User with given e-mail address
     */
    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam String email) {
        System.out.println("User with e-mail: " + email + "passed to the request");
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * This method returns a list of users older than a given birth date.
     * 
     * @param birthDate Birth date to compare
     * @return List of users older than given birth date
     */
    @GetMapping("/older/{birthDate}")
    public List<UserDto> getUsersOlderThan(@PathVariable String birthDate) {
        return userService.findOlderThan(birthDate)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

}