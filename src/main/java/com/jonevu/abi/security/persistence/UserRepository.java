package com.jonevu.abi.security.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonevu.abi.security.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
