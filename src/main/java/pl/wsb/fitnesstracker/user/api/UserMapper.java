/*
 ____            _            _____ _             _     _      ___ ___ ___ ___ ___
|    \ ___ _____|_|___ ___   |   __| |_ ___ ___  |_|___| |_   | . | . | . |_  |  _|
|  |  | .'|     | | .'|   |  |__   |  _|  _| . | | | -_| '_|  |_  |_  | . |_  | . |
|____/|__,|_|_|_|_|__,|_|_|  |_____|_| |_| |___|_| |___|_,_|  |___|___|___|___|___|
                                               |___|
 */
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

    /**
     * Maps UserDto to User entity with state of previous entity
     * @param userDto UserDto
     * @param user User
     *
     * @return User
     */
    public User toUpdateEntity(UserDto userDto, User user) {
        if(userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }

        if(userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }

        if(userDto.birthdate() != null) {
            user.setBirthdate(userDto.birthdate());
        }

        if(userDto.email() != null) {
            user.setEmail(userDto.email());
        }

        return user;
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
