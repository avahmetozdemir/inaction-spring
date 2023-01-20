package com.lawman.inaction.user.dto;

public class UserDto {

    String mail;
    String firstname;
    String middleName;
    String lastname;

    public UserDto(String mail, String firstname, String middleName, String lastname) {
        this.mail = mail;
        this.firstname = firstname;
        this.middleName = middleName;
        this.lastname = lastname;
    }
}
