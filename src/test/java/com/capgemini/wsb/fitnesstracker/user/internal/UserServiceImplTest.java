package com.capgemini.wsb.fitnesstracker.user.internal;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }
    @Test
    @Transactional
    public void shouldAddNewUser() {
        UserDto userDto = new UserDto(11L, "Steven", "Tyler", LocalDate.of(1948,3,26), "steven.tyler@aerosmith.com");
        int usersListSize = userService.findAllUsers().size();
        User createdUser = userService.createUser(userMapper.toEntity(userDto));
        assertNotNull(createdUser.getId());
        assertEquals(usersListSize + 1, userService.findAllUsers().size());
        assertEquals(userDto.firstName(), createdUser.getFirstName());
        assertEquals(userDto.lastName(), createdUser.getLastName());
        assertEquals(userDto.birthdate(), createdUser.getBirthdate());
        assertEquals(userDto.email(), createdUser.getEmail());
    }
    @Test
    @Transactional
    public void shouldDeleteUser() {
        UserDto userDto = new UserDto(11L, "Steven", "Tyler", LocalDate.of(1948,3,26), "steven.tyler@aerosmith.com");
        User createdUser = userService.createUser(userMapper.toEntity(userDto));
        int initialUserCount = userService.findAllUsers().size();
        userService.removeUser(createdUser);
        assertEquals(initialUserCount - 1, userService.findAllUsers().size());
    }

    @Test
    @Transactional
    public void shouldUpdateUser() {
        UserDto userDto = new UserDto(11L, "Steven", "Tyler", LocalDate.of(1948,3,26), "steven.tyler@aerosmith.com");
        User createdUser = userService.createUser(userMapper.toEntity(userDto));
        UserDto updatedUserDto = new UserDto(createdUser.getId(), "Stephen", "Tyler", LocalDate.of(1948,3,26), "stephentyler1948@aerosmith.com");
        userService.updateUser(createdUser.getId(), userMapper.toEntity(updatedUserDto));
        User updatedUser = userService.findUserById(createdUser.getId()).orElse(null);
        assertNotNull(updatedUser);
        assertEquals(updatedUserDto.firstName(), updatedUser.getFirstName());
        assertEquals(updatedUserDto.email(), updatedUser.getEmail());
    }

    @Test
    @Transactional
    public void shouldFindUserByEmail() {
        // Przygotowanie
        UserDto userDto = new UserDto(11L, "Steven", "Tyler", LocalDate.of(1948,3,26), "steven.tyler@aerosmith.com");
        userService.createUser(userMapper.toEntity(userDto));

        // Działanie
        Optional<User> foundUserOptional = userService.findUserByEmail("steven.tyler@aerosmith.com");

        // Asercje
        assertTrue(foundUserOptional.isPresent());
        User foundUser = foundUserOptional.get();
        assertEquals("Steven", foundUser.getFirstName());
        assertEquals("Tyler", foundUser.getLastName());
        assertEquals(LocalDate.of(1948, 3, 26), foundUser.getBirthdate());
        assertEquals("steven.tyler@aerosmith.com", foundUser.getEmail());
    }
}

