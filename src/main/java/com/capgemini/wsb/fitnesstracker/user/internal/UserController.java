package com.capgemini.wsb.fitnesstracker.user.internal;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUsers().stream()
                .map(user -> userMapper.toDto(user)) // wywołanie metody toDto() na instancji userMapper
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
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

//    @PutMapping("/{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
//        User updatedUser = userService.updateUser(id, userMapper.toEntity(userDto));
//        return ResponseEntity.ok(userMapper.toDto(updatedUser));
//    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") Long id,
            @RequestBody UserDto userDto
    ) {
        Optional<User> optionalUser = userService.getUser(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userDto.firstName());
            user.setLastName(userDto.lastName());
            user.setBirthdate(userDto.birthdate());
            user.setEmail(userDto.email());

            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok().body(userMapper.toDto(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findUserById(id);
        if (optionalUser.isPresent()) {
            UserDto userDto = userMapper.toDto(optionalUser.get());
            return ResponseEntity.ok(userDto);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

}