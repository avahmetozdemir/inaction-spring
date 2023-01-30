package com.lawman.inaction.user;

import com.lawman.inaction.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {
    public static Long userId = 100L;
    public static List<Users> generateUsers() {
      return  IntStream.range(0,5).mapToObj(i ->
            new Users((long) i, i+"@gmail.com" ,"firstName"+ i, "", "lastName" + i, new Random(2).nextBoolean())
        ).collect(Collectors.toList());
    }

    public static List<UserDto> generateUserDtoList(List<Users> usersList) {
        return  usersList.stream().map(x ->  new UserDto(x.getMail(),x.getFirstName(), x.getMiddleName(),x.getLastName(),new ArrayList<>())).collect(Collectors.toList());
    }

    public static Users generateUser(String mail){
        return new Users((long) userId, userId+"@gmail.com" ,"firstName"+ userId, "", "lastName" + userId, true);
    }

    public static UserDto generateUserDto(String mail){

        return new UserDto(mail,"firstName"+ userId, "", "lastName" ,new ArrayList<>());

    }
}
