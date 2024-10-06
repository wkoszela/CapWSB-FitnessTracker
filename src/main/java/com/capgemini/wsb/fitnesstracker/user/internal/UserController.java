package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    public Object getUsers(@RequestParam Map<String, String> params) {
        if (params.isEmpty()){
            // Return all users if no params are provided
            return userService.findAllUsers();
        }
        if (params.size() > 1){
            return "Please provide only one parameter"; // REQUEST
        }
        return switch(params.keySet().toArray()[0].toString()) {
            // Return the single user if id is provided
            case "id" -> userService.getUser(Long.parseLong(params.get("id")));

            // Bad param
            default -> "No param found";
        };
    }



    @PostMapping
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        // TODO: saveUser with Service and return User
        return null;
    }

}