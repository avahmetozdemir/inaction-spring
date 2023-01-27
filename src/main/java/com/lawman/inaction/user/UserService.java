package com.lawman.inaction.user;

import com.lawman.inaction.user.dto.CreateUserRequest;
import com.lawman.inaction.user.dto.UpdateUserRequest;
import com.lawman.inaction.user.dto.UserDto;
import com.lawman.inaction.user.dto.UserDtoConverter;
import com.lawman.inaction.user.exception.UserIsNotActiveException;
import com.lawman.inaction.user.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository,UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter= userDtoConverter;
    }


    public List<UserDto> getAllUsers() {
        return userDtoConverter.convert(userRepository.findAll());

    }

    public UserDto getUserById(Long id) {
        User user = findUserById(id);
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(CreateUserRequest userRequest) {
        User user  = new User(userRequest.getMail(), userRequest.getFirstName(), userRequest.getMiddleName(), userRequest.getLastName(),false);

        return userDtoConverter.convert(userRepository.save(user));

    }

    public UserDto updateUser(String mail, UpdateUserRequest updateUserRequest) {
        User user = findUserByMail(mail);
        if(!user.getActive()) {
            logger.warn(String.format("User is not active to update!, user mail: %s", mail));
            throw new UserIsNotActiveException("User is not active to update!");
        }
        User updatedUser = new User( user.getId(),user.getMail(), updateUserRequest.getFirstName(),updateUserRequest.getMiddleName(),updateUserRequest.getLastName(),user.getActive());

        return userDtoConverter.convert(userRepository.save(updatedUser));

    }

    private User findUserByMail(String mail) {
      return userRepository.findByMail(mail).orElseThrow(()->new UserNotFoundException("User couldn't be found by following mail: " + mail));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(("User couldn't be found by following id: " + id)));
    }

    public void deactivateUser(Long id) {
       changeActivationUser(id, false);
    }



    public void activateUser(Long id) {
        changeActivationUser(id, true);
    }

    public void deleteUser(Long id) {
       findUserById(id);
       userRepository.deleteById(id);
    }

    private void  changeActivationUser(Long id, Boolean isActive) {
        User user  = findUserById(id);

        User updatedUser = new User( user.getId(),user.getMail(), user.getFirstName(),user.getMiddleName(),user.getLastName(),isActive);

        userRepository.save(updatedUser);
    }



    public UserDto getUserByMail(String mail) {
        User user = findUserByMail(mail);
        return userDtoConverter.convert(user);
    }
}
