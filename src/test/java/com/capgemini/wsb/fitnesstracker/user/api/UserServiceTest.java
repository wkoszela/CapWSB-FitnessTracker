package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.FitnessTracker;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = FitnessTracker.class)

public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Autowired

    private UserRepository userRepository;


    @Test
    void createUser() throws UserAlreadyExistException {
        User user = new User("Lukasz", "User", LocalDate.of(1996, 06, 21), "example@gmail.com");
        User created = userService.createUser(user);
        assertNotNull(created);
        assertNotNull(created.getId());



    }

    @Test
    void getUser() {
        User user =userRepository.save(new User("Testowy", "User", LocalDate.of(1996,06,21), "blabla@blala.com"));
        Optional<User> found = userService.getUser(user.getId());
        assertTrue(found.isPresent());
        assertEquals(user.getEmail(), found.get().getEmail());


    }

    @Test
    void getUserMail() {
        User user = userRepository.save(new User("MailUser", "User", LocalDate.of(1996,06,21),"blabla@blabla.com"));
        Optional<User> found = userService.getUserByEmail(user.getEmail());
        assertTrue(found.isPresent());
        assertEquals(user.getEmail(),found.get().getEmail());
    }

    @Test
    void findAllUser() {
        userRepository.save(new User("Summ", "Lukasz", LocalDate.of(1996,06,21),"testest@gmail.com"));
        userRepository.save(new User("Summ2", "Lukasz2", LocalDate.of(1996,06,21),"testest2@gmail.com"));
        userRepository.save(new User("Summ3", "Lukasz3", LocalDate.of(1996,06,21),"testest3@gmail.com"));

        List<User> users =userService.findAllUser();
        assertFalse(users.isEmpty());
    }

    @Test
    void userUpdate() {
        User user = userRepository.save(new User("UpdateUser", "Lukasz", LocalDate.of(1996, 6, 21), "testowz@gmail.com"));
        User user1 = new User("UpdatedUser", "LukaszUpdate", user.getBirthdate(), user.getEmail());
        User updatedUser = userService.userUpdate(user.getId(), user1);
        assertNotNull(updatedUser);
        assertEquals("UpdatedUser", updatedUser.getFirstName());
        assertEquals("LukaszUpdate", updatedUser.getLastName());
    }

    @Test
    void findByMailCase() {
        User user = userRepository.save(new User("Email", "Case", LocalDate.of(1996,6,21),"emailtofound@gmail.com"));
        List<User> users = userService.findByMailCase("Emailtofound@gmail.com");
        assertFalse(users.isEmpty());
        assertTrue(users.stream().anyMatch(user1 -> user1.getEmail().equalsIgnoreCase(user.getEmail())));
    }


    @Test
    void findUsersOlderThan_ShouldFindUsers() {
        // GIVEN
        User olderUser = userRepository.save(new User("Older", "User", LocalDate.now().minusYears(30), "olderuser@example.com"));
        User youngerUser = userRepository.save(new User("Younger", "User", LocalDate.now().minusYears(10), "youngeruser@example.com"));

        // WHEN
        List<User> users = userService.findUsersOlderThan(20);

        // THEN
        boolean foundedOlderUser = false;
        boolean foundedYoungerUser = false;
        for (User user : users){
            if(user.getFirstName() == olderUser.getFirstName() && user.getEmail() == olderUser.getEmail()){
                foundedOlderUser = true;
            }
            if(user.getFirstName() == youngerUser.getFirstName() && user.getEmail() == youngerUser.getEmail()){
                foundedYoungerUser= true;
            }
        }

        assertTrue(foundedOlderUser);
        assertFalse(foundedYoungerUser);
    }
    @Test
    void delete() {
        User user = userRepository.save(new User("LukaszToDelete", "Trash", LocalDate.of(1996,6,21),"ToDelete@gmail.com"));
        boolean del = userService.delete(user.getId());
        assertTrue(del);
    }
}