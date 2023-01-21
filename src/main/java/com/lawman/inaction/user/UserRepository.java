package com.lawman.inaction.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    public Optional<User> findByMail(String mail);
}
