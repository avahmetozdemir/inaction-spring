package com.lawman.inaction.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {


    public Optional<Users> findByMail(String mail);// user attributeleri ile ilgili bir method oluşturuduğumdan jpa otomatik olarak devreye girip bunu bize sağlıyor.
}
