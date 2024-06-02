package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserBasicDto;
import org.springframework.stereotype.Component;

@Component
class UserBasicMapper {

    UserBasicDto toDto(User user) {
        return new UserBasicDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }
}
