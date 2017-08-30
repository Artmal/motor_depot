package com.artmal.model;

import com.artmal.model.enums.Role;

/**
 * Simple Java Bean for representing User entity.
 * @author Artem Malchenko
 */
public class User {
    private long id;
    private String username;
    private String password;
    private transient String confirmPassword;
    private Role role;

    public User() {
    }

    public User(long id, String username, String password, String confirmPassword, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}