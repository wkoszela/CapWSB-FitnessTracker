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

//   @GetMapping
//    public User addUser(@RequestBody UserDto userDto) {
//
//        // Demonstracja how to use @RequestBody
//        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");
//        return userService.createUser(userMapper.toEntity(userDto));




//    }
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/user/list")
    public List<UserSummDto> getAllUsersSummary() {
        return userService.findAllUser()
                .stream()
                .map(user -> new UserSummDto(user.getFirstName() + " " + user.getLastName(), user.getId()))
                .toList();
    }
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
@GetMapping("/user/search")
public List<UserSummDto> searchUsersByEmail(@RequestParam String email) {return userService.findByMailCase(email)
                .stream()
                .map(user -> new UserSummDto(user.getEmail(), user.getId()))
                .toList();
    }}