package pl.wsb.fitnesstracker.user.internal;

import pl.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserSimpleMapper {
    /**
     * Maps User entity to UserSimpleDto
     * @param user User
     * @return UserSimpleDto
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    /**
     * Maps UserSimpleDto to User entity
     * @param userDto UserSimpleDto
     * @return User
     */
    User toSimpleEntity(UserSimpleDto userDto) {
        return new User(userDto.firstName(), userDto.lastName(), null, null);
    }
}