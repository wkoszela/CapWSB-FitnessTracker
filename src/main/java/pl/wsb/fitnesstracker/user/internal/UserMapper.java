package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserBasicDto;

/**
 * Simple mapper that converts between {@link User} entities and the
 * various DTOs used by the API layer.
 *
 * <p>All methods are straightforward and side‑effect free.  They can
 * be replaced by a library such as MapStruct if the mapping logic becomes
 * more complex.</p>
 */
@Component
class UserMapper {

    /**
     * Converts a {@link UserDto} into a new {@link User} entity.
     *
     * @param dto data transfer object with user information
     * @return new {@link User} instance (without id)
     */
    public User toEntity(UserDto dto) {
        return new User(dto.firstName(), dto.lastName(),
                dto.birthdate(), dto.email());
    }

    /**
     * Converts a {@link User} entity into a full DTO.
     *
     * @param user source entity
     * @return {@link UserDto} containing all fields
     */
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(),
                user.getLastName(), user.getBirthdate(), user.getEmail());
    }

    /**
     * Converts a {@link User} into the lightweight basic DTO.
     *
     * @param user source entity
     * @return {@link UserBasicDto}
     */
    public UserBasicDto toBasicDto(User user) {
        return new UserBasicDto(user.getId(), user.getFirstName(),
                user.getLastName());
    }

    /**
     * Converts a {@link User} into the e‑mail DTO.
     *
     * @param user source entity
     * @return {@link UserEmailDto}
     */
    public UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }
}
