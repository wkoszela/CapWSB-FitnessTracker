package pl.wsb.fitnesstracker.user.internal;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;

import java.time.LocalDate;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Test
    void shouldThrowUserNotFoundException_whenUpdatingUser() throws Exception {
        // given
        UserServiceImpl sut = new UserServiceImpl(userRepository);
        User userInfo = new User("a", "b", LocalDate.now(), "d");
        Long userID = 12L;
        Mockito.when(userRepository.getReferenceById(userID)).thenThrow(new EntityNotFoundException());

        // expect
        Assertions.assertThrows(
                UserNotFoundException.class,
                () -> sut.updateUser(userID, userInfo),
                "Expected UserNotFound exception");
    }
}