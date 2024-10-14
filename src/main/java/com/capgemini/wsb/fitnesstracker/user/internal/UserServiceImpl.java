package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.CreateUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.UpdateUserDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import com.capgemini.wsb.fitnesstracker.user.api.UserSummaryDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public List<UserSummaryDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserSummaryDto(user.getId(), user.getFirstName()))
                .collect(Collectors.toList());
    }

    @Override
public UserSummaryDto createUser(CreateUserDto createUserDto) {
    // Tworzenie nowego obiektu User z użyciem konstruktora
    User newUser = new User(
        createUserDto.firstName(),
        createUserDto.lastName(),
        createUserDto.birthdate(),
        createUserDto.email()
    );

    // Zapis nowego użytkownika w bazie danych
    User savedUser = userRepository.save(newUser);

    // Zwrócenie UserSummaryDto z zapisanym ID i username
    return new UserSummaryDto(savedUser.getId(), savedUser.getFirstName());
}

@Override
    public List<UserSummaryDto> getUsersOlderThan(int age) {
        LocalDate currentDate = LocalDate.now();

        return userRepository.findAll()
                .stream()
                .filter(user -> {
                    // Obliczanie wieku użytkownika na podstawie daty urodzenia
                    int userAge = Period.between(user.getBirthdate(), currentDate).getYears();
                    return userAge > age;
                })
                .map(user -> new UserSummaryDto(user.getId(), user.getFirstName()))
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
public UserSummaryDto updateUser(Long id, UpdateUserDto updateUserDto) {
    // Znalezienie użytkownika po ID
    User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

    // Aktualizacja atrybutów, jeśli są podane w updateUserDto
    if (updateUserDto.firstName() != null) {
        user.updateFirstName(updateUserDto.firstName());
    }
    if (updateUserDto.lastName() != null) {
        user.updateLastName(updateUserDto.lastName());
    }
    if (updateUserDto.email() != null) {
        user.updateEmail(updateUserDto.email());
    }
    if (updateUserDto.birthdate() != null) {
        user.updateBirthdate(updateUserDto.birthdate());
    }

    // Zapisanie zaktualizowanego użytkownika
    User updatedUser = userRepository.save(user);

    // Zwrócenie zaktualizowanego użytkownika jako DTO
    return new UserSummaryDto(updatedUser.getId(), updatedUser.getFirstName());
}

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}