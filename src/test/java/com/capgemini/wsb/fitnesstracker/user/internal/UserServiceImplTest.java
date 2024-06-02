package com.capgemini.wsb.fitnesstracker.user.internal;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
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

    @Test
    @Transactional
    public void shouldFindUsersOlderThanX() {
        UserDto userDto1 = new UserDto(null, "Steven", "Tyler", LocalDate.of(1948, 3, 26), "steven.tyler@aerosmith.com");
        UserDto userDto2 = new UserDto(null, "Mick", "Jagger", LocalDate.of(1943, 7, 26), "mick.jagger@rollingstones.com");
        UserDto userDto3 = new UserDto(null, "Small", "Baby", LocalDate.of(2023, 1, 1), "young.user@example.com");

        userService.createUser(userMapper.toEntity(userDto1));
        userService.createUser(userMapper.toEntity(userDto2));
        userService.createUser(userMapper.toEntity(userDto3));

        int ageThreshold = 70;
        Collection<User> olderUsers = userService.findUserOlderThanX(ageThreshold);

        assertTrue(olderUsers.stream().allMatch(user -> {
            int userAge = Period.between(user.getBirthdate(), LocalDate.now()).getYears();
            return userAge > ageThreshold;
        }));
        assertEquals(2, olderUsers.size()); // Should only return Steven Tyler and Mick Jagger
    }
}

