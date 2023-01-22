package com.lawman.inaction.user;

import com.lawman.inaction.user.dto.UserDtoConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserServiceTest {
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
    public void testGetAllUsers_itShouldReturnUserDtoList() {

    }

}