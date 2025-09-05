package com.nyanmyohtet.tdd.service;

import com.nyanmyohtet.tdd.model.User;
import com.nyanmyohtet.tdd.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }
}

