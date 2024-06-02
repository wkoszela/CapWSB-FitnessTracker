package com.capgemini.wsb.fitnesstracker.user.internal;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping("/simple/{id}")
//    public List<UserDto> getAllUsers() {
//        return userService.findAllUsers()
//                          .stream()
//                          .map(userMapper::toDto)
//                          .toList();
//    }

    public List<UserSimpleDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("/simple")
    public ResponseEntity<List<UserSimpleDto>> getAllSimpleUsers() {
        List<UserSimpleDto> users = userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User createdUser = userService.createUser(userMapper.toEntity(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(createdUser));
    }

    @GetMapping("/user/{id}")
    public Optional<UserDto> getAllUsers(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userService.findUserById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "This user not found")));
        return user.map(UserMapper::toDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto){
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");
        User addingUser = userService.createUser(userMapper.toEntity(userDto));
        // TODO: saveUser with Service and return User
        return ResponseEntity.ok().body(addingUser);
    }


    @GetMapping("/older/{time}")
    public ResponseEntity<List<UserDto>> findUsersOlderThanX(@PathVariable("time") int age) {
        List<UserDto> users = userService.findUserOlderThanX(age).stream().map(UserMapper::toDto).toList();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found older than " + age);
        } else {
            return ResponseEntity.ok().body(users);
        }
    }



    @GetMapping("/email")
    public ResponseEntity<List<UserDto>> findUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.findUserByEmail(email);
        if (user.isPresent()) {
            List<UserDto> userDtoList = List.of(userMapper.toDto(user.get()));
            return ResponseEntity.ok(userDtoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> removeUser(@PathVariable Long id) {
        Optional<User> user =  userService.findUserById(id);
        if(user.isPresent())
        {
            userService.removeUser(user.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userMapper.toEntity(userDto));
        return ResponseEntity.ok(userMapper.toDto(updatedUser));
    }
}