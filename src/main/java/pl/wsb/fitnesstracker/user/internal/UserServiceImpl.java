package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     * tworzy uzytkownika
     * @param user The user to be created
     * @return
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     * szuka usera po id
     * @param userId id of the user to be searched
     * @return
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findByID(userId);
    }

    /**
     * szuka usera po email lub fragmencie
     * @param email The email of the user to be searched
     * @return
     */
    @Override
    public List<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * szuka wszystkich uzytkownikow
     * @return
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllSummary();
    }

    /**
     * udpateuje dane uzytkownika
     * @param id
     * @param dto
     */
    void update(Long id, UserUpdateDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow();

        if (dto.firstName() != null) user.setFirstName(dto.firstName());
        if (dto.lastName() != null) user.setLastName(dto.lastName());
        if (dto.email() != null) user.setEmail(dto.email());
        if (dto.birthdate() != null) user.setBirthdate(dto.birthdate());

        userRepository.save(user);
    }

    /**
     * usuwa uzytkownika
     * @param id
     */
    void delete(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * szuka uzytkownikow urodzonych przed data
     * @param date
     * @return
     */
    public List<User> getUserByAge(final LocalDate date) {
        return userRepository.findOlderThan(date);
    }
}