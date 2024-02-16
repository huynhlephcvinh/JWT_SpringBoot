package com.example.SpringJwtAuthentication.service;

import com.example.SpringJwtAuthentication.model.Role;
import com.example.SpringJwtAuthentication.model.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName name);
}
