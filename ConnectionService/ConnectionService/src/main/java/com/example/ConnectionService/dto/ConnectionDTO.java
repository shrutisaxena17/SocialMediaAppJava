package com.example.ConnectionService.dto;

import com.example.ConnectionService.entity.ConnectionStatus;

public class ConnectionDTO {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private ConnectionStatus status;

    public ConnectionDTO() {
    }

    public ConnectionDTO(Long id, Long senderId, Long receiverId, ConnectionStatus status) {
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

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public ConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectionStatus status) {
        this.status = status;
    }
}
