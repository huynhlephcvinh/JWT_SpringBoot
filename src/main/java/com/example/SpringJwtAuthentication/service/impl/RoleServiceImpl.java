package com.example.SpringJwtAuthentication.service.impl;

import com.example.SpringJwtAuthentication.model.Role;
import com.example.SpringJwtAuthentication.model.RoleName;
import com.example.SpringJwtAuthentication.repository.RoleRepository;
import com.example.SpringJwtAuthentication.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
