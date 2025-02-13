package com.example.ConnectionService.dto;

public class ConnectionEventDTO {
    private String senderId;
    private String receiverId;
    private String action;

    public ConnectionEventDTO() {}

    public ConnectionEventDTO(String senderId, String receiverId, String action) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.action = action;
    }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getReceiverId() { return receiverId; }
    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    @Override
    public String toString() {
        return "ConnectionEventDTO{" +
                "senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
