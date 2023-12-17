package com.jonevu.abi.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jonevu.abi.security.model.User;
import com.jonevu.abi.security.service.IUserService;
import com.jonevu.abi.security.validation.EmailExistsException;


@Component
public class SetupBean {

    @Autowired
    private IUserService userService;

    //

    @PostConstruct
    public void setupUser() throws EmailExistsException {
        final User user = new User("admin@fake.com", "adminpass");
        userService.registerNewUser(user);
    }

}
