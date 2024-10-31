package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Retrieves all users as a list of UserDto objects.
     *
     * @return a list of all users converted to UserDto
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves basic information for all users as a list of UserBaseInfo objects.
     *
     * @return a list of basic information for all users
     */
    @GetMapping("/simple")
    public List<UserBaseInfo> getAllUsersInfoList() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toUserBaseInfo)
                .toList();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the user converted to UserDto
     * @throws UserNotFoundException if the user is not found
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Retrieves users by a specific email address as a list of UserIdEmailInfo objects.
     *
     * @param email the email address to search for
     * @return a list of users with the specified email address
     */
    @GetMapping("/email")
    public List<UserIdEmailInfo> getUserByHisEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toUserIdEmail)
                .toList();
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user
     * @return the user converted to UserDto
     * @throws UserNotFoundException if no user with the specified email is found
     */
    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with EMAIL=%s was not found".formatted(email)));
    }

    /**
     * Retrieves a user by their first and last name.
     *
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @return the user converted to UserDto
     * @throws UserNotFoundException if no user with the specified first and last name is found
     */
    @GetMapping("/namesurname/{firstName}&{lastName}")
    public UserDto getUserByNameSurname(@PathVariable String firstName, @PathVariable String lastName) {
        return userService.getUserByNameSurname(firstName, lastName)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with NAME=%s, SURNAME=%s was not found".formatted(firstName, lastName)));
    }

    /**
     * Retrieves users by their birthdate as a list of UserIdBirthdayInfo objects.
     *
     * @param birthday the birthdate to filter users by
     * @return a list of users with the specified birthdate
     */
    @GetMapping("/birthdate")
    public List<UserIdBirthdayInfo> getUserByBirthdate(@RequestParam @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthday) {
        return userService.getUserByBirthdate(birthday)
                .stream()
                .map(userMapper::toUserIdBirthdayDto)
                .toList();
    }

    /**
     * Adds a new user to the database.
     *
     * @param userDto the UserDto object containing the user data to add
     * @return the created User object
     * @throws InterruptedException if the process is interrupted
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return userService.createUser(userMapper.toEntity(userDto));
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}