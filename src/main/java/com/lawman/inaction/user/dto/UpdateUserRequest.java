package com.lawman.inaction.user.dto;

public class UpdateUserRequest{
   private String mail;
   private String firstName;
   private String middleName;
   private String lastName;


    public UpdateUserRequest(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
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
