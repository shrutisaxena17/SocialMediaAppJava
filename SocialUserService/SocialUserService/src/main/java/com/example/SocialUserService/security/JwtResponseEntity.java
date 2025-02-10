package com.example.SocialUserService.security;

public class JwtResponseEntity {
    String message;
    String token;

    public JwtResponseEntity() {
    }

    public JwtResponseEntity(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
