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


    @GetMapping("/findUser/{age}")
    public ResponseEntity<List<UserDto>> findUsersOlderThanX(@PathVariable("age") int age) {
        List<UserDto> users = userService.findUserOlderThanX(age).stream().map(UserMapper::toDto).toList();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found older than " + age);
        } else {
            return ResponseEntity.ok().body(users);
        }
    }


    @GetMapping("/findEmail/{email}")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable("email") String email) {
        User user = userService.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This user not found"));
        UserDto userDto = userMapper.toDto(user);
        return ResponseEntity.ok().body(userDto);
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