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

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }
}
