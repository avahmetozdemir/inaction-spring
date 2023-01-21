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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
