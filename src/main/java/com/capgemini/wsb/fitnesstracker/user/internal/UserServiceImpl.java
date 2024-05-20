package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserAlreadyExistException;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) throws UserAlreadyExistException {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        Optional<User> userSameMail =this.getUserByEmail(user.getEmail());
        if (userSameMail.isPresent()){
            throw new UserAlreadyExistException(user.getEmail());
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
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User userUpdate(Long id, User newUserData) {
        return userRepository.findById(id).map(existUser ->{
            if (newUserData.getLastName() != null) {
                existUser.setLastName(newUserData.getLastName());
            }
            if (newUserData.getFirstName() != null) {
                existUser.setFirstName(newUserData.getFirstName());
            }
            if (newUserData.getEmail() != null) {
                existUser.setEmail(newUserData.getEmail());
            }
            if (newUserData.getBirthdate() != null) {
                existUser.setBirthdate(newUserData.getBirthdate());
            }
            log.info("Update {}", existUser);
            return userRepository.save(existUser);
        }).orElseThrow(() -> new EntityNotFoundException("User id not found " + id));
    }



    /**
     * Finds all users who are older than the specified age.
     *
     * @param age The age threshold.
     * @return A list of {@link User} entities who are older than the specified age.
     */
    public List<User> findUsersOlderThan(int age) {
        LocalDate thresholdDate = LocalDate.now().minusYears(age);
        return userRepository.findAllByBirthdateBefore(thresholdDate);
    }


    @Override
    public List<User> findByMailCase(String emailCase) {
        return userRepository.findByEmailIgnoreCase(emailCase);
    }



    @Override
    public boolean delete(Long userId) {
     if(userRepository.existsById(userId)){
         userRepository.deleteById(userId);
         return true;
     }
     return false;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}