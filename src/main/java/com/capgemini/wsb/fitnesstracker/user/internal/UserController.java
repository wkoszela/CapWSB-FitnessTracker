package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping("full")
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }
    @GetMapping("simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("single/{id}")
    public List<UserDto> getSingleUser(@PathVariable long id) {
        return userService.getUser(id)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping("add")
    public User addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userService.createUser(user);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable long id) {
        if(userService.getUser(id).isPresent()) {
            userService.deleteUser(id);
        }
    }

    @GetMapping("email")
    public List<UserDto> getUserByEmail(@RequestBody String email) {
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("older_then/{age}")
    public List<UserDto> getUserOlderThen(@PathVariable int age) {
        return userService.getOlderThen(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping("set_name/{id}")
    public List<UserDto> addUser(@PathVariable long id, @RequestBody String newName) {
        userService.updateUser(id, newName);

        return userService.getUser(id)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}