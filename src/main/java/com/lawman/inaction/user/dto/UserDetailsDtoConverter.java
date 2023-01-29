package com.lawman.inaction.user.dto;

import com.lawman.inaction.user.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsDtoConverter {

    public UserDetailsDto convert(UserDetails from){
        return new UserDetailsDto(from.getPhoneNumber(), from.getAddress(), from.getCity(),from.getCountry(), from.getPostCode());
    }

    public List<UserDetailsDto> convert(List<UserDetails> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
