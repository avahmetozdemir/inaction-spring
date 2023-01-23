package com.lawman.inaction.user;

import com.lawman.inaction.user.dto.UserDto;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {
    public static Long userId = 100L;
    public static List<User> generateUsers() {
      return  IntStream.range(0,5).mapToObj(i ->
            new User((long) i, i+"@gmail.com" ,"firstName"+ i, "", "lastName" + i, new Random(2).nextBoolean())
        ).collect(Collectors.toList());
    }

    public static List<UserDto> generateUserDtoList(List<User> userList) {
        return  userList.stream().map(x ->  new UserDto(x.getMail(),x.getFirstName(), x.getMiddleName(),x.getLastName())).collect(Collectors.toList());
    }

    public static User generateUser(String mail){
        return new User((long) userId, userId+"@gmail.com" ,"firstName"+ userId, "", "lastName" + userId, true);
    }

    public static UserDto generateUserDto(String mail){

        return new UserDto(mail,"firstName"+ userId, "", "lastName" );

    }
}
