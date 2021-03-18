package com.telkom.training.dto;

import java.util.Date;

public class UserDTO {
    private String username;
    private String email;
    private String roles;
    private Date create_at;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return this.roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getCreate_at() {
        return this.create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

}
