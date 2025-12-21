package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserByMailDTO;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserSumDTO;

@Component
class UserMapper {
    /**
     * mapuje dane usera do UserDto
     * @param user
     * @return
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * mapuje dane usera do UserByMailDTO
     * @param user
     * @return
     */
    UserByMailDTO toMailDTO(User user) {
        return new UserByMailDTO(user.getId(),
                user.getEmail());
    }

    /**
     * mapuje dane usera do UserSumDTO
     * @param user
     * @return
     */
    UserSumDTO toSumDTO(User user) {
        return new UserSumDTO(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * mapuje z UserDto do User
     * @param dto
     * @return
     */
    User fromCreate(UserDto dto) {
        return new User(dto.firstName(), dto.lastName(), dto.birthdate(), dto.email());
    }

}
