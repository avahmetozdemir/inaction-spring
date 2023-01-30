package com.lawman.inaction.user.dto;

import java.util.List;

public class UserDto {

    String mail;
    String firstname;
    String middleName;
    String lastname;

    List<UserDetailsDto> userDetails;

    public UserDto(String mail, String firstname, String middleName, String lastname,List<UserDetailsDto> userDetails) {
        this.mail = mail;
        this.firstname = firstname;
        this.middleName = middleName;
        this.lastname = lastname;
        this.userDetails=userDetails;
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

    public List<UserDetailsDto> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetailsDto> userDetails) {
        this.userDetails = userDetails;
    }
}
