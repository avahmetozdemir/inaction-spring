package com.lawman.inaction.user;

import com.lawman.inaction.user.dto.CreateUserRequest;
import com.lawman.inaction.user.dto.UpdateUserRequest;
import com.lawman.inaction.user.dto.UserDto;
import com.lawman.inaction.user.dto.UserDtoConverter;
import com.lawman.inaction.user.exception.UserNotFoundException;
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
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());//???map fonsiyonu ile ne yapılıyor anla.
    }

    public UserDto getUserById(Long id) {
        User user = findUserById(id);
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(CreateUserRequest userRequest) {
        User user  = new User(userRequest.getMail(), userRequest.getFirstName(), userRequest.getMiddleName(), userRequest.getLastName());

        return userDtoConverter.convert(userRepository.save(user));

    }

    public UserDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = findUserById(id);
        User updatedUser = new User(user.getId(), user.getMail(), user.getFirstName(),user.getMiddleName(),user.getLastName());

        return userDtoConverter.convert(userRepository.save(updatedUser));

    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(("User couldn't be found by following id: " + id)));
    }
}
