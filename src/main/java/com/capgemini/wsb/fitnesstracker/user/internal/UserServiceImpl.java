package com.capgemini.wsb.fitnesstracker.user.internal;
import org.springframework.web.server.ResponseStatusException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Collection;
import java.util.Optional;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User with existing ID!");
        }
        return userRepository.save(user);
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

    @Override
    public Optional<User> findUserById(Long userId){
        return userRepository.findById(userId);
    }

    @Override
    public  void removeUser(final User user){
        log.info("User {} removed", user.getId());
        if(user.getId() == null){
            throw new IllegalArgumentException("This user doesn't exist!");
        }
        userRepository.delete(user);
    }

    @Override
    public User addUser(final User user){
        log.info("Adding user :{}", user);
        User thisUser = userRepository.findById(user.getId()).get();
        thisUser.setEmail(user.getEmail());
        thisUser.setFirstName(user.getFirstName());
        thisUser.setLastName(user.getLastName());
        userRepository.save(thisUser);
        return  user;

    }
    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    //wtedy List


    @Override
    public Collection<User> findUserOlderThanX(Integer age) {
        return userRepository.findUserOlderThanX(age);
    }

    @Override
    public User updateUser(User user) {
        User userToUpdate = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + user.getId()));

        // Update the user fields
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setBirthdate(user.getBirthdate());
        userToUpdate.setEmail(user.getEmail());

        return userRepository.save(userToUpdate);
    }
}