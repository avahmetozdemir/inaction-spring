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
        Users users = findUserById(id);
        return userDtoConverter.convert(users);
    }

    public UserDto createUser(CreateUserRequest userRequest) {
        Users users = new Users(userRequest.getMail(), userRequest.getFirstName(), userRequest.getMiddleName(), userRequest.getLastName(),false);

        return userDtoConverter.convert(userRepository.save(users));

    }

    public UserDto updateUser(String mail, UpdateUserRequest updateUserRequest) {
        Users users = findUserByMail(mail);
        if(!users.getActive()) {
            logger.warn(String.format("User is not active to update!, user mail: %s", mail));
            throw new UserIsNotActiveException("User is not active to update!");
        }
        Users updatedUsers = new Users( users.getId(), users.getMail(), updateUserRequest.getFirstName(),updateUserRequest.getMiddleName(),updateUserRequest.getLastName(), users.getActive());

        return userDtoConverter.convert(userRepository.save(updatedUsers));

    }

    private Users findUserByMail(String mail) {
      return userRepository.findByMail(mail).orElseThrow(()->new UserNotFoundException("User couldn't be found by following mail: " + mail));
    }

    private Users findUserById(Long id) {
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
        Users users = findUserById(id);

        Users updatedUsers = new Users( users.getId(), users.getMail(), users.getFirstName(), users.getMiddleName(), users.getLastName(),isActive);

        userRepository.save(updatedUsers);
    }



    public UserDto getUserByMail(String mail) {
        Users users = findUserByMail(mail);
        return userDtoConverter.convert(users);
    }
}
