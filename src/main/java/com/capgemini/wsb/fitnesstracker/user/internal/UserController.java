package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDTO;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserBasicDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserEmailDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    private final UserBasicMapper userBasicMapper;

    private final UserEmailMapper userEmailMapper;

    /**
     * Retrieves all users with basic information.
     *
     * @return a list of {@link UserBasicDto}
     */
    @GetMapping("/simple")
    public List<UserBasicDto> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userBasicMapper::toDto)
                .toList();
    }

    /**
     * Retrieves all users with detailed information.
     *
     * @return a list of {@link UserDto}
     */
    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to retrieve
     * @return the {@link UserDto} of the user
     * @throws UserNotFoundException if the user with the specified ID is not found
     */
    @GetMapping("/{userID}")
    public UserDto getUserById(@PathVariable("userID") final Long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return userMapper.toDto(user.get());
        }
        throw new UserNotFoundException(id);
    }

    /**
     * Creates a new user.
     *
     * @param userDto the DTO of the user to create
     * @return the created {@link User}
     * @throws InterruptedException if the creation process is interrupted
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        User createduser = userService.createUser(userMapper.toEntity(userDto));
        return createduser;
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     * @return the deleted {@link User}
     * @throws UserNotFoundException if the user with the specified ID is not found
     */
    @DeleteMapping("/{userID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User deleteUser(@PathVariable("userID") final Long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return userService.deleteUser(user.get());
        }
        throw new UserNotFoundException(id);
    }

    /**
     * Searches for users by email fragment (case-insensitive).
     *
     * @param emailFragment the fragment of the email to search for
     * @return a list of {@link UserBasicDto} with ID and email only
     */
    @GetMapping("/email")
    public List<UserEmailDto> searchUsersByEmailFragment(@RequestParam("emailFragment") String emailFragment) {
        return userService.findUsersByEmailFragment(emailFragment)
                .stream()
                .map(userEmailMapper::toDto)
                .toList();
    }

    /**
     * Finds users older than a specified date.
     *
     * @param time the date to compare against
     * @return a list of {@link UserDto}
     */
    @GetMapping("/older/{time}")
    public List<UserDto> findUsersOlder(@PathVariable("time") LocalDate time) {
        return userService.findUsersOlder(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserDto user) {
        User updateUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updateUser);
    }
}