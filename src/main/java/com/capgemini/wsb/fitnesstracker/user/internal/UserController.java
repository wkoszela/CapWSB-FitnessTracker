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

    /**
     * Retrieves all users from the user service and maps them to a list of UserSimpleDto objects.
     *
     * @return  a list of UserSimpleDto objects representing all users
     */
    @GetMapping
    public List<UserSimpleDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toSimpleDto)
                          .toList();
    }

    /**
     * Retrieves a user with the specified ID from the user service and maps it to a UserDto object.
     *
     * @param  id  the ID of the user to retrieve
     * @return     the UserDto object representing the user with the specified ID, or throws a ResponseStatusException if the user is not found
     */
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id)
                          .map(userMapper::toDto)
                          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
    }

    /**
     * Adds a new user to the system.
     *
     * @param  userDto  the user data to be added, passed in the request body
     * @return          the created user object
     */
    @PostMapping
    public User addUser(@RequestBody UserDto userDto) {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        return userService.createUser(userMapper.toEntity(userDto));
    }

    /**
     * Deletes a user with the specified ID from the user service.
     *
     * @param  id  the ID of the user to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        User user = userService.getUser(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
        userService.deleteUser(user);
    }

    /**
     * Retrieves users with the specified email from the user service and maps them to a list of UserEmailDto objects.
     *
     * @param  email  the email address to search for in user emails
     * @return        a list of UserEmailDto objects representing the users with the specified email
     */
    @GetMapping("/email/{email}")
    public List<UserEmailDto> getUsersByEmail(@PathVariable String email) {
        return userService.findUsersByEmail(email)
                          .stream()
                          .map(userMapper::toEmailDto)
                          .toList();
    }

    /**
     * Retrieves users with an age greater than or equal to the specified minimum age from the user service and maps them to a list of UserSimpleDto objects.
     *
     * @param  minAge  the minimum age of the users to search for
     * @return         a list of UserSimpleDto objects representing the users with an age greater than or equal to the specified minimum age
     */
    @GetMapping("/minAge/{minAge}")
    public List<UserSimpleDto> getUsersByMinAge(@PathVariable int minAge) {
        return userService.findUsersByMinAge(minAge)
                          .stream()
                          .map(userMapper::toSimpleDto)
                          .toList();
    }

    /**
     * Updates a user with the specified ID in the user service.
     *
     * @param  id       the ID of the user to update
     * @param  userDto  the updated user data, passed in the request body
     * @return          the updated user object
     */
    @PostMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userService.getUser(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found"));
        return userService.updateUser(userMapper.toEntityWithId(userDto, id));
    }
}