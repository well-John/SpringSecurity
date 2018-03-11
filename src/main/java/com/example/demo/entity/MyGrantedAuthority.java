package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;

/**
 * Created by yangyibo on 17/2/15.
 */
public class MyGrantedAuthority implements GrantedAuthority {


    private List<Authority> authorities;

    private String role;

    public MyGrantedAuthority() {
    }

    public MyGrantedAuthority(List<Authority> authorities, String role) {
        this.authorities = authorities;
        this.role = role;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}