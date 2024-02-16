package com.example.SpringJwtAuthentication.service;

import com.example.SpringJwtAuthentication.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    User existsByUserName(String username);
//    Boolean exitByUsername(String username);
//    Boolean exitByEmail(String email);
}
