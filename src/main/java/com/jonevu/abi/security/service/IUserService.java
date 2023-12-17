package com.jonevu.abi.security.service;

import com.jonevu.abi.security.model.User;
import com.jonevu.abi.security.validation.EmailExistsException;

public interface IUserService {

    // read

    User findByEmail(String email);

    // write

    User registerNewUser(User user) throws EmailExistsException;

}
