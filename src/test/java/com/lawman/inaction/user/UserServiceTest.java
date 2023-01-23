package com.lawman.inaction.user;

import com.lawman.inaction.user.dto.CreateUserRequest;
import com.lawman.inaction.user.dto.UpdateUserRequest;
import com.lawman.inaction.user.dto.UserDto;
import com.lawman.inaction.user.dto.UserDtoConverter;
import com.lawman.inaction.user.exception.UserIsNotActiveException;
import com.lawman.inaction.user.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest extends TestSupport {
    private UserDtoConverter converter;
    private UserRepository repository;

    private UserService userService;

    @BeforeEach
    public void setUp(){
        converter = mock(UserDtoConverter.class);
        repository = mock(UserRepository.class);

        userService= new UserService(repository, converter);
    }

    @Test
    public void testGetAllUsers_itShouldReturnUserDto() {
        List<User> userList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(userList);
        when(repository.findAll()).thenReturn(userList);
        when(converter.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(userDtoList, result);
        verify(repository).findAll();
        verify(converter).convert(userList);



    }

    @Test
    public void testGetUserByMail_whenUserMailExist_itShouldReturnUserDto() {
        String mail= "mail@gmail.com";

        User user = generateUser(mail);
        UserDto userDto= generateUserDto(mail);
        when(repository.findByMail(mail)).thenReturn(Optional.of(user));
        when(converter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getUserByMail(mail);

        assertEquals(userDto, result);
        verify(repository).findByMail(mail);
        verify(converter).convert(user);



    }

    @Test
    public void testGetUserByMail_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException() {
        String mail= "mail@gmail.com";


        when(repository.findByMail(mail)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, ()-> userService.getUserByMail(mail));



        verify(repository).findByMail(mail);
        verifyNoInteractions(converter);


    }


    @Test
    public void testCreateUser_itShouldReturnCreatedUserDto() {
        String mail= "mail@gmail.com";
        CreateUserRequest request = new CreateUserRequest(mail, "firstName", "middleName","lastName" );
        User user =  new User(mail,"firstName", "middleName","lastName",false );
        User savedUser = new User(1L, mail, "firstName", "middleName","lastName",false );
        UserDto userDto = new UserDto(mail, "firstName", "middleName","lastName");

        when(repository.save(user)).thenReturn(savedUser);
        when(converter.convert(savedUser)).thenReturn(userDto);

        UserDto result = userService.createUser((request));


        assertEquals(userDto,result);


        verify(repository).save(user);
        verify(converter).convert(savedUser);


    }

    @Test
    public void testUpdateUser_whenUserMailExistAndUserIsActive_itShouldReturnUpdatedUserDto() {
        String mail= "mail@gmail.com";
        UpdateUserRequest request = new UpdateUserRequest( "firstName2", "middleName2","lastName2" );
        User user =  new User(1L,mail,"firstName", "middleName","lastName",true );
        User updatedUser = new User(1L, mail, "firstName2", "middleName2","lastName2",true );
        User savedUser = new User(1L, mail, "firstName2", "middleName2","lastName2",true );
        UserDto userDto = new UserDto(mail, "firstName2", "middleName2","lastName2");

        when(repository.findByMail(mail)).thenReturn(Optional.of(user));
        when(repository.save(updatedUser)).thenReturn(savedUser);
        when(converter.convert(savedUser)).thenReturn(userDto);

        UserDto result = userService.updateUser(mail, request);


        assertEquals(userDto,result);

        verify(repository).findByMail(mail);
        verify(repository).save(updatedUser);
        verify(converter).convert(savedUser);


    }

    @Test
    public void testUpdateUser_whenUserMailExist_itShouldThrowUserNotFoundException() {
        String mail= "mail@gmail.com";
        UpdateUserRequest request = new UpdateUserRequest( "firstName2", "middleName2","lastName2" );


        when(repository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(mail, request));



        verify(repository).findByMail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);


    }

    @Test
    public void testUpdateUser_whenUserMailExistButUserIsNotActive_itShouldThrowUserNotActiveException() {
        String mail= "mail@gmail.com";
        UpdateUserRequest request = new UpdateUserRequest( "firstName2", "middleName2","lastName2" );
        User user =  new User(1L,mail,"firstName", "middleName","lastName",false );


        when(repository.findByMail(mail)).thenReturn(Optional.of(user));

        assertThrows(UserIsNotActiveException.class, () -> userService.updateUser(mail, request));



        verify(repository).findByMail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);


    }


    @Test
    public void testDeactivateUser_whenUserIdExist_itShouldUpdateUserByActiveTrue() {
        String mail= "mail@gmail.com";

        User user =  new User(userId,mail,"firstName", "middleName","lastName",true );
        User savedUser =  new User(userId,mail,"firstName", "middleName","lastName",false );

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.deactivateUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUser);

    }

    @Test
    public void testDeactivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, ()->userService.deactivateUser(userId) );

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

    @Test
    public void testActivateUser_whenUserIdExist_itShouldUpdateUserByActiveTrue() {
        String mail= "mail@gmail.com";

        User user =  new User(userId,mail,"firstName", "middleName","lastName",false );
        User savedUser =  new User(userId,mail,"firstName", "middleName","lastName",true );

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.activateUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUser);

    }

    @Test
    public void testActivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, ()->userService.activateUser(userId) );

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

}