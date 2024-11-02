package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
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

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        return userService.createUser(userMapper.toEntity(userDto));
    }

    @GetMapping("/simple")
    public List<UserNameDto> getAllUsersSimple() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toNameDto)
                          .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id)
                          .map(userMapper::toDto)
                          .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userMapper.toEntity(userDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @GetMapping("/email-partial-match")
    public List<UserEmailDto> getUserByEmailPartialMatch(@RequestParam String emailFragment) {
        return userService.findByEmailPartialMatch(emailFragment)
                          .stream()
                          .map(userMapper::toEmailDto)
                          .toList();
    }

    @GetMapping("/older/{date}")
    public List<UserDto> getAllUsersOlderThan(@PathVariable LocalDate date) {
        return userService.findUsersBornBefore(date)
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }
}
