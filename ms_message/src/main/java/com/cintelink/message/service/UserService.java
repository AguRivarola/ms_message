package com.cintelink.message.service;

import com.cintelink.message.model.User;
import com.cintelink.message.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);
    private UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getExistingUser(String user){
        return repository.selectUserByUserName(user);
    }
}
