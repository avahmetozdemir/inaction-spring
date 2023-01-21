package com.lawman.inaction.user.dto;

public class CreateUserRequest {
    String mail;
    String firstName;
    String middleName;
    String lastName;

    Boolean isActive;

    public CreateUserRequest() {
    }

    public Boolean getActive() {
        return isActive;
    }

    public CreateUserRequest(String mail, String firstName, String middleName, String lastName, Boolean isActive) {
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.isActive = isActive;
    }



    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
