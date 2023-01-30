package com.lawman.inaction.user;

import com.lawman.inaction.user.dto.CreateUserRequest;
import com.lawman.inaction.user.dto.UpdateUserRequest;
import com.lawman.inaction.user.dto.UserDto;
import com.lawman.inaction.user.dto.UserDtoConverter;
import com.lawman.inaction.user.exception.UserIsNotActiveException;
import com.lawman.inaction.user.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsersServiceTest extends TestSupport {
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
        List<Users> usersList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(usersList);
        when(repository.findAll()).thenReturn(usersList);
        when(converter.convert(usersList)).thenReturn(userDtoList);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(userDtoList, result);
        verify(repository).findAll();
        verify(converter).convert(usersList);



    }

    @Test
    public void testGetUserByMail_whenUserMailExist_itShouldReturnUserDto() {
        String mail= "mail@gmail.com";

        Users users = generateUser(mail);
        UserDto userDto= generateUserDto(mail);
        when(repository.findByMail(mail)).thenReturn(Optional.of(users));
        when(converter.convert(users)).thenReturn(userDto);

        UserDto result = userService.getUserByMail(mail);

        assertEquals(userDto, result);
        verify(repository).findByMail(mail);
        verify(converter).convert(users);



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
        Users users =  new Users(mail,"firstName", "middleName","lastName",false );
        Users savedUsers = new Users(1L, mail, "firstName", "middleName","lastName",false );
        UserDto userDto = new UserDto(mail, "firstName", "middleName","lastName", new ArrayList<>());

        when(repository.save(users)).thenReturn(savedUsers);
        when(converter.convert(savedUsers)).thenReturn(userDto);

        UserDto result = userService.createUser((request));


        assertEquals(userDto,result);


        verify(repository).save(users);
        verify(converter).convert(savedUsers);


    }

    @Test
    public void testUpdateUser_whenUserMailExistAndUserIsActive_itShouldReturnUpdatedUserDto() {
        String mail= "mail@gmail.com";
        UpdateUserRequest request = new UpdateUserRequest( "firstName2", "middleName2","lastName2" );
        Users users =  new Users(1L,mail,"firstName", "middleName","lastName",true );
        Users updatedUsers = new Users(1L, mail, "firstName2", "middleName2","lastName2",true );
        Users savedUsers = new Users(1L, mail, "firstName2", "middleName2","lastName2",true );
        UserDto userDto = new UserDto(mail, "firstName2", "middleName2","lastName2",new ArrayList<>());

        when(repository.findByMail(mail)).thenReturn(Optional.of(users));
        when(repository.save(updatedUsers)).thenReturn(savedUsers);
        when(converter.convert(savedUsers)).thenReturn(userDto);

        UserDto result = userService.updateUser(mail, request);


        assertEquals(userDto,result);

        verify(repository).findByMail(mail);
        verify(repository).save(updatedUsers);
        verify(converter).convert(savedUsers);


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
        Users users =  new Users(1L,mail,"firstName", "middleName","lastName",false );


        when(repository.findByMail(mail)).thenReturn(Optional.of(users));

        assertThrows(UserIsNotActiveException.class, () -> userService.updateUser(mail, request));



        verify(repository).findByMail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);


    }


    @Test
    public void testDeactivateUser_whenUserIdExist_itShouldUpdateUserByActiveTrue() {
        String mail= "mail@gmail.com";

        Users users =  new Users(userId,mail,"firstName", "middleName","lastName",true );
        Users savedUsers =  new Users(userId,mail,"firstName", "middleName","lastName",false );

        when(repository.findById(userId)).thenReturn(Optional.of(users));

        userService.deactivateUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUsers);

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

        Users users =  new Users(userId,mail,"firstName", "middleName","lastName",false );
        Users savedUsers =  new Users(userId,mail,"firstName", "middleName","lastName",true );

        when(repository.findById(userId)).thenReturn(Optional.of(users));

        userService.activateUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUsers);

    }

    @Test
    public void testActivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, ()->userService.activateUser(userId) );

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

    @Test
    public void testDeleteUser_whenUserIdExist_itShouldDeleteUser() {
        String mail= "mail@gmail.com";

        Users users =  new Users(userId,mail,"firstName", "middleName","lastName",false );

        when(repository.findById(userId)).thenReturn(Optional.of(users));

        userService.deleteUser(userId);

        verify(repository).findById(userId);
        verify(repository).deleteById(userId);

    }

    @Test
    public void testDeleteUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, ()->userService.deleteUser(userId) );

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

}