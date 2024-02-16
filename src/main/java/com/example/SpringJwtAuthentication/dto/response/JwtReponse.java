package com.example.SpringJwtAuthentication.dto.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtReponse {
    private Long id;
    private String token;
    private String name;
    private String type="Bearer";
    private Collection<? extends GrantedAuthority> roles;

    public JwtReponse(String token, String name, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.name = name;
        this.roles = roles;
    }

    public JwtReponse(Long id, String token, String name, String type, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.type = type;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
