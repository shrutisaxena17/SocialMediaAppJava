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


    public Users() {
        this.id = UUID.randomUUID().toString();
    }

    public Users(String id, String email, boolean isDeleted) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.isDeleted = isDeleted;
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
}
