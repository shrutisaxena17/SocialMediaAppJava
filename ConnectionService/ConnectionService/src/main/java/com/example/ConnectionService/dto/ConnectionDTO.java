package com.example.ConnectionService.dto;

import com.example.ConnectionService.entity.ConnectionStatus;

public class ConnectionDTO {
    private Long id;
    private String senderId;
    private String receiverId;
    private ConnectionStatus status;

    public ConnectionDTO() {
    }

    public ConnectionDTO(Long id, String senderId, String receiverId, ConnectionStatus status) {
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

    @Override
    public String toString() {
        return "ConnectionDTO{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", status=" + status +
                '}';
    }
}
