package com.lawman.inaction.user;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column(unique = true)
    private String mail;
    private String firstName;
    private String middleName;
    private String lastName;

    private Boolean isActive;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY, cascade =CascadeType.ALL )
    private Set<UserDetails> userDetailsSet;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Users() {
    }

    public Users(Long id, String mail, String firstName, String middleName, String lastName) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Users(String mail, String firstName, String middleName, String lastName, Boolean isActive) {
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.isActive = isActive;
    }

    public Users(Long id, String mail, String firstName, String middleName, String lastName, Boolean isActive) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.isActive = isActive;
    }

    public Users(Long id, String mail, String firstName, String middleName, String lastName, Boolean isActive, Set<UserDetails> userDetailsSet) {
        this.id = id;
        this.mail = mail;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.userDetailsSet = userDetailsSet;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(mail, users.mail) && Objects.equals(firstName, users.firstName) && Objects.equals(middleName, users.middleName) && Objects.equals(lastName, users.lastName) && Objects.equals(isActive, users.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, firstName, middleName, lastName, isActive);
    }
}
