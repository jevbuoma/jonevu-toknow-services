package com.jonevu.abi.security.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonevu.abi.security.model.User;
import com.jonevu.abi.security.persistence.UserRepository;
import com.jonevu.abi.security.validation.EmailExistsException;
import com.jonevu.abi.util.DateUtil;


@Service
@Transactional
class UserService implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
    @Autowired
    private UserRepository repository;

    // write

    @Override
    public User registerNewUser(final User user) throws EmailExistsException {
        if (emailExist(user.getEmail())) {
        	// uncomment the code below and remove the return statement under normal circumstance ..
            //throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
        	logger.error("There is an account with that email address: " + user.getEmail());
        	return new User();
        }
    	user.setEnabled(true);
    	user.setCreated(DateUtil.getCurrentDate());
        return repository.save(user);
    }

    // read

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    //

    private boolean emailExist(String email) {
        final User user = repository.findByEmail(email);
        return user != null;
    }

}
