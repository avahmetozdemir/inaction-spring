package com.lawman.inaction.user.dto;

import com.lawman.inaction.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public  UserDto convert(User from) {
        return new UserDto(from.getMail(),from.getFirstName(), from.getMiddleName(),from.getLastName());
    }

    public  List<UserDto> convert(List<User> fromList) {
        return fromList.stream().map(from -> new UserDto(from.getMail(),from.getFirstName(), from.getMiddleName(),from.getLastName())).collect(Collectors.toList());
    }
}
