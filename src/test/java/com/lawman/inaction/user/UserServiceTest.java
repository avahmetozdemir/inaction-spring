package com.lawman.inaction.user;

import com.lawman.inaction.user.dto.UserDto;
import com.lawman.inaction.user.dto.UserDtoConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest extends TestSupport {
    private UserDtoConverter converter;
    private UserRepository repository;

    private UserService userService;

    @BeforeAll
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
    public void testGetUserByMail_whenUserMailExist_itShouldReturnUserDtoList() {
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

}