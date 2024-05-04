package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserAlreadyExistException;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
        return userService.findAllUser()
                          .stream()
                .map(user -> new UserOverallDTO(user.getId(),
                        user.getFirstName() + " " + user.getLastName()))
                .toList();

    }

//   @GetMapping
//    public User addUser(@RequestBody UserDto userDto) {
//
//        // Demonstracja how to use @RequestBody
//        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");
//        return userService.createUser(userMapper.toEntity(userDto));




//    }




    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(user -> ResponseEntity.ok(userMapper.toDto(user))).orElse(ResponseEntity.notFound().build());

    }
    @PostMapping("user/add")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto)throws UserAlreadyExistException{
        User user = userMapper.toEntity(userDto);
        return ResponseEntity.ok(userService.createUser(user));
    }

}