package com.lawman.inaction.user.dto;

import com.lawman.inaction.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public  UserDto convert(User from) {
        return new UserDto(from.getMail(),from.getFirstName(), from.getMiddleName(),from.getLastName());
    }
}
