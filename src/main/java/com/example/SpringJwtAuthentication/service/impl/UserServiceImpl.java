package com.example.SpringJwtAuthentication.service.impl;

import com.example.SpringJwtAuthentication.model.User;
import com.example.SpringJwtAuthentication.repository.UserRepository;
import com.example.SpringJwtAuthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User existsByUserName(String username) {
        return userRepository.existsByUserName(username);
    }

//    @Override
//    public Boolean exitByUsername(String username) {
//        return userRepository.exitByUsername(username);
//    }
//
//    @Override
//    public Boolean exitByEmail(String email) {
//        return userRepository.exitByEmail(email);
//    }
}
