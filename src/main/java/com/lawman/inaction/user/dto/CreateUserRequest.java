package com.lawman.inaction.user.dto;

public class CreateUserRequest {
    String mail;
    String firstName;
    String middleName;
    String lastName;

    public CreateUserRequest(String mail, String firstName, String middleName, String lastName) {
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
}
