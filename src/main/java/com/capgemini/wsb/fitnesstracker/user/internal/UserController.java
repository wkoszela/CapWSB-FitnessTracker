package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.CreateUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UpdateUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserSummaryDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public ResponseEntity<List<UserSummaryDto>> getAllUsers() {
        List<UserSummaryDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Dodaje nowego użytkownika do systemu.
     * 
     * @param userDto obiekt UserDto zawierający dane nowego użytkownika
     * @return obiekt User utworzony w systemie
     * @throws InterruptedException jeżeli wystąpi problem podczas przetwarzania
     */
    @PostMapping
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        // Demonstracja jak używać @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + " passed to the request");

        // TODO: saveUser with Service and return User
        return null;
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
     * Pobiera listę użytkowników starszych niż określony wiek.
     * 
     * @param age wiek, który musi być przekroczony przez użytkowników
     * @return ResponseEntity zawierające listę użytkowników starszych niż określony wiek
     */
    @GetMapping("/older-than/{age}")
    public ResponseEntity<List<UserSummaryDto>> getUsersOlderThan(@PathVariable int age) {
        List<UserSummaryDto> users = userService.getUsersOlderThan(age);
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
