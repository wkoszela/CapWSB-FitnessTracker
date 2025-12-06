package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.user.api.BasicUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserIdEmail;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
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

    public List<BasicUserDto> findAllUsersWithBasicInformation() {
        return userRepository.findAllUsersWithBasicData().stream()
                .map(
                        user ->
                                new BasicUserDto(
                                        user.getId(), user.getFirstName(), user.getLastName()))
                .toList();
    }

    public UserDto findUserById(Long id) {
        User user = userRepository.findUserById(id);
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    @Transactional
    public User createUser(final UserDto user) {
        log.info("Creating User {}", user);
        User entity = new User(user.firstName(), user.lastName(), user.birthdate(), user.email());
        return userRepository.save(entity);
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<UserIdEmail> findUserByEmail(String email){
        List<User> users = userRepository.findUsersByEmailContainingIgnoreCase(email);
        return users.stream().map(user -> new UserIdEmail(user.getId(), user.getEmail())).toList();
    }
}
