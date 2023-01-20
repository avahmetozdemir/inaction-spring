package com.lawman.inaction.user.dto;

public class UpdateUserRequest{
    String firstName;
    String middleName;
    String lastName;

    public UpdateUserRequest(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
}
