package com.lawman.inaction.user;

import com.lawman.inaction.user.dto.UserDto;
import com.lawman.inaction.user.dto.UserDtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository,UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter= userDtoConverter;
    }


    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }
}
