package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {


private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/user/list")
    public List<UserOverallDTO> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                .map(user -> new UserSummaryDto(user.getId(), user.getFirstName() + " " + user.getLastName()))                          .toList();

    }

    @PostMapping
    public User addUser(@RequestBody UserDto userDto) {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");
        return userService.createUser(userMapper.toEntity(userDto));




    }


}