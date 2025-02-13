package com.example.ConnectionService.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "connections")
public class ConnectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String senderId;

    @Column(nullable = false)
    private String receiverId;

    @Enumerated(EnumType.STRING)
    private ConnectionStatus status;

    public ConnectionEntity() {
    }

    public ConnectionEntity(Long id, String senderId, String receiverId, ConnectionStatus status) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public ConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectionStatus status) {
        this.status = status;
    }
}
