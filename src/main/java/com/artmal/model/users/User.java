package com.artmal.model.users;

import com.artmal.model.enums.Role;
import lombok.Data;

import java.util.Date;

/**
 * Simple Java Bean for representing {@link Driver} entity.
 * @author Artem Malchenko
 */
public @Data class User {
    private long id;
    private String email;
    private String password;
    private Date dateOfRegistration;
    /**
     * Which administrator registered this user.
     */
    private long regAdminId;
    private Role role;

    public User() {
    }

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(long id, String email, String password, long regAdminId, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.dateOfRegistration = dateOfRegistration;
        this.regAdminId = regAdminId;
        this.role = role;
    }

    public User(String email, String password, long regAdminId, Role role) {
        this.email = email;
        this.password = password;
        this.dateOfRegistration = dateOfRegistration;
        this.regAdminId = regAdminId;
        this.role = role;
    }
}