package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.CreateUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UpdateUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserSummaryDto;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Kontroler odpowiedzialny za operacje na użytkownikach, takie jak tworzenie, aktualizacja oraz 
 * pobieranie listy użytkowników.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Pobiera listę wszystkich użytkowników w systemie.
     *
     * @return ResponseEntity zawierające listę użytkowników w postaci obiektów UserSummaryDto
     */
    @GetMapping("/simple")
    public ResponseEntity<List<UserSummaryDto>> getAllUsers() {
        List<UserSummaryDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/details")
    public ResponseEntity<List<UserDto>> getUserDetails(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate,
            @RequestParam(required = false) String email) {
        List<com.capgemini.wsb.fitnesstracker.user.api.UserDto> userDetails = userService.getUserDetails(id, firstName, lastName, birthdate, email);
        return ResponseEntity.ok(userDetails);
    }


    /**
     * Tworzy nowego użytkownika w systemie.
     * 
     * @param createUserDto obiekt CreateUserDto zawierający dane nowego użytkownika
     * @return ResponseEntity zawierające obiekt UserSummaryDto nowo utworzonego użytkownika
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserSummaryDto> createUser(@RequestBody CreateUserDto createUserDto) {
        UserSummaryDto newUser = userService.createUser(createUserDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
 * Pobiera listę użytkowników urodzonych przed określoną datą.
 * 
 * @param time data, przed którą użytkownicy muszą się urodzić
 * @return ResponseEntity zawierające listę użytkowników urodzonych przed określoną datą
 */
@GetMapping("/older/{time}")
public ResponseEntity<List<UserOlderThanDto>> getUsersBornBefore(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
    List<UserOlderThanDto> users = userService.getUsersBornBefore(time);
    return ResponseEntity.ok(users);
}

    /**
     * Aktualizuje dane istniejącego użytkownika.
     * 
     * @param id identyfikator użytkownika, którego dane mają zostać zaktualizowane
     * @param updateUserDto obiekt UpdateUserDto zawierający zaktualizowane dane użytkownika
     * @return ResponseEntity zawierające zaktualizowany obiekt UserSummaryDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserSummaryDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {
        UserSummaryDto updatedUser = userService.updateUser(id, updateUserDto);
        return ResponseEntity.ok(updatedUser);
    }
}
