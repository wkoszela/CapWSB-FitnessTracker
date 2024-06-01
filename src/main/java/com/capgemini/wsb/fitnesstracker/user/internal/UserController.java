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
import java.util.Collection;

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
        // return userService.findUserById(id).map(userMapper::toDto);
        Optional<User> user = Optional.ofNullable(userService.findUserById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return user.map(userMapper::toDto);
    }

//     @PostMapping
//     public User addUser(@RequestBody UserDto userDto) throws InterruptedException {



    @PostMapping("/{id}")
    //public User addUser(@RequestBody UserDto userDto) {
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto){
        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");
        User addingUser = userService.createUser(userMapper.toEntity(userDto));
        // TODO: saveUser with Service and return User
        //return userService.createUser(userMapper.toEntity(userDto));
        return ResponseEntity.ok().body(addingUser);
    }

    @PostMapping("/findUser/age")
    public ResponseEntity<List<UserDto>> findUsersOlderThanX(@RequestParam int age) {
        List<UserDto> users = userService.findUserOlderThanX(age).stream().map(userMapper::toDto).toList();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/findUser/email")
    public User findUserByEmail(@RequestBody UserEmailDto mail) {
        return userService.findUserByEmail(mail.email()).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public Optional<UserDto> removeUser(@PathVariable Long id) {
        Optional<User> user =  userService.findUserById(id);
        if(user.isPresent())
        {
            userService.removeUser(user.get());
            return user.map(userMapper::toDto);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userMapper.toEntity(userDto));
        return ResponseEntity.ok(userMapper.toDto(updatedUser));
    }
}