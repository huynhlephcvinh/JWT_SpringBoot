package com.example.SpringJwtAuthentication.controller;

import com.example.SpringJwtAuthentication.dto.request.SignInForm;
import com.example.SpringJwtAuthentication.dto.request.SignUpForm;
import com.example.SpringJwtAuthentication.dto.response.JwtReponse;
import com.example.SpringJwtAuthentication.dto.response.ResponseData;
import com.example.SpringJwtAuthentication.model.Role;
import com.example.SpringJwtAuthentication.model.RoleName;
import com.example.SpringJwtAuthentication.model.User;
import com.example.SpringJwtAuthentication.repository.UserRepository;
import com.example.SpringJwtAuthentication.security.jwt.JwtProvider;
import com.example.SpringJwtAuthentication.security.userprincal.UserPrinciple;
import com.example.SpringJwtAuthentication.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("signup")
    public ResponseEntity<?> gignup(@RequestBody SignUpForm signUpForm) {
        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok().body(new ResponseData<>("", 200, "Create user success"));
    }

    @PostMapping("signin")
    public ResponseEntity<?> login(@RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok().body(new JwtReponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }

}
