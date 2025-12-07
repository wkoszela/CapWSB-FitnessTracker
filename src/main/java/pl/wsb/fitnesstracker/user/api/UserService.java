package pl.wsb.fitnesstracker.user.api;

public interface UserService {

    User createUser(User user);

    void deleteUser(Long userId);

    User updateUser(Long userId, User user);

}
