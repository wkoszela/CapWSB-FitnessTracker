package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public Object getUser(@PathVariable("id") long id) {
        var user = userService.getUser(id);
        return user.isPresent() ? user : new ResponseStatusException(404, "No user found with this id", null);
    }

    @GetMapping
    public Object getUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public Object addUser(@RequestBody UserDto userDto) throws InterruptedException {

        if(userService.getUserByEmail(userDto.email()).isPresent()) {
            return new ResponseStatusException(409, "User already exists", null);
        }

        System.out.println("User with e-mail: " + userDto.email() + " passed to the request");


        // saveUser with Service and return User or Exception
        try{
            return userService.createUser(userMapper.toEntity(userDto));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseStatusException(400, "Error creating new user", e);
        }
    }

}
