package com.example.todoo.services;

import com.example.todoo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String loginOrSignup(String name, String email) {
        return userRepository.insertOrGetUser(name, email);
    }
}
