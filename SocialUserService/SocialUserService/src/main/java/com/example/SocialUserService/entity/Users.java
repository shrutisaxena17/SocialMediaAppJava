package com.example.SocialUserService.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Users {

    @Id
    private String id;

    private String email;
    private String password;
    private boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;


    public Users() {
        this.id = UUID.randomUUID().toString();
    }

    public Users(String id, String email, boolean isDeleted, UserRole role) {
        this.id = UUID.randomUUID().toString();
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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
