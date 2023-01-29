package com.lawman.inaction.user.dto;

import com.lawman.inaction.user.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public  UserDto convert(Users from) {
        return new UserDto(from.getMail(),from.getFirstName(), from.getMiddleName(),from.getLastName());
    }

    public  List<UserDto> convert(List<Users> fromList) {
        return fromList.stream().map(from -> new UserDto(from.getMail(),from.getFirstName(), from.getMiddleName(),from.getLastName())).collect(Collectors.toList());
    }
}
