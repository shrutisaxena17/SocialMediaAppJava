package com.example.SocialUserService.dto;

import com.example.SocialUserService.entity.UserRole;

public class UsersDTO {
    private String id;
    private String email;
    private String password;
    private boolean isDeleted;
    private UserRole role; 

    public UsersDTO() {
    }

    public UsersDTO(String id, String email, boolean isDeleted, UserRole role) {
        this.id = id;
        this.email = email;
        this.isDeleted = isDeleted;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
