package pl.wsb.fitnesstracker.user.api;

import org.springframework.stereotype.Component;

import pl.wsb.fitnesstracker.user.internal.UserSimpleDto;

@Component
public class UserMapper {

    /**
     * Maps User entity to UserDto
     * @param user User
     * @return UserDto
     */
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     * Maps UserDto to User entity
     * @param userDto UserDto
     * @return User
     */
    public User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }


    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    public UserDetailsDto toDetailsDto(User user) {
        return new UserDetailsDto(user.getFirstName(), user.getBirthdate(), user.getEmail());
    }

    UserIdEmailDto toUserIdEmailDto(User user) {
        return new UserIdEmailDto(user.getId(), user.getEmail());
    }

}
