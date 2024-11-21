package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserBasicInfoDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserIdAndEmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/simple")
    public List<UserBasicInfoDto> getUserBasicInfo() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toBasicInfoDto)
                .toList();
    }

    @GetMapping(value = "/{id}")
    public List<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping(value = "/email")
    public List<UserDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


    @GetMapping(value = "/partial-email")
    public List<UserIdAndEmailDto> getUserByPartialEmail(@RequestParam String email) {
        return userService.findUsersByPartialEmail(email)
                .stream()
                .map(userMapper::toIdAndEmailDto)
                .toList();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws InterruptedException {
        User user = new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
        return ResponseEntity.status(201).body(userMapper.toDto(userService.createUser(user)));
    }

    @GetMapping(value = "/older/{date}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate date) {
        return userService.findUsersByBirthdateGreaterThan(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto updatedUser) {
        User updatedUserResponse = userService.updateUser(id, updatedUser);

        if (updatedUserResponse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(updatedUserResponse));
    }
}