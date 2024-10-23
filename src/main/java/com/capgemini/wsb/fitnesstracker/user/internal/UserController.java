package com.capgemini.wsb.fitnesstracker.user.internal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * @return List<UserDto>
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * @return List<UserSimpleDto>
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getSimpleAllUser() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimple)
                .toList();
    }

    /**
     * @param userId
     * @return UserDto
     */
    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userMapper.toDto(userService.getUser(userId).orElseThrow());
    }

    /**
     * @param email
     * @return List<UserEmailDto>
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toEmail)
                .toList();
    }

    /**
     * @param time
     * @return List<UserOlderDto>
     */
    @GetMapping("/older/{time}")
    public List<UserOlderDto> getUsersOlderThan(@PathVariable @JsonFormat(pattern = "yyyy-MM-dd") LocalDate time) {
        return userService.findAllUsers()
                .stream()
                .filter(user -> user.getBirthdate().isBefore(time))
                .map(userMapper::toOlder)
                .toList();
    }

    /**
     * @param userDto
     * @return User
     * @throws InterruptedException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");
        return userService.createUser(userMapper.toEntity(userDto));
    }

    /**
     * @param userId
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * @param userId
     * @param userDto
     * @return User
     */
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User existingUser = userService.getUser(userId).orElseThrow(() -> new UserNotFoundException(userId));
        User updatedUser = userMapper.updateEntity(existingUser, userDto);
        return userService.updateUser(updatedUser);
    }

}
