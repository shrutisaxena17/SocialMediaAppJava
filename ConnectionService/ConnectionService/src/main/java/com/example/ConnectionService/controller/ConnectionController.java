package com.example.ConnectionService.controller;

import com.example.ConnectionService.dto.ConnectionDTO;
import com.example.ConnectionService.service.ConnectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping("/{receiverId}")
    public ResponseEntity<ConnectionDTO> sendConnectionRequest(@RequestParam Long senderId, @PathVariable Long receiverId) {
        ConnectionDTO connection = connectionService.sendConnectionRequest(senderId, receiverId);
        return ResponseEntity.status(201).body(connection);
    }

    @PutMapping("/{requestId}/accept")
    public ResponseEntity<ConnectionDTO> acceptConnection(@PathVariable Long requestId) {
        ConnectionDTO connection = connectionService.acceptConnection(requestId);
        return ResponseEntity.ok(connection);
    }

    @DeleteMapping("/{connectionId}")
    public ResponseEntity<Void> removeConnection(@PathVariable Long connectionId) {
        connectionService.removeConnection(connectionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/pending")
    public ResponseEntity<List<ConnectionDTO>> getPendingRequests(@PathVariable Long userId) {
        return ResponseEntity.ok(connectionService.getPendingRequests(userId));
    }

    @GetMapping("/{userId}/connections")
    public ResponseEntity<List<ConnectionDTO>> getConnections(@PathVariable Long userId) {
        return ResponseEntity.ok(connectionService.getConnections(userId));
    }
}
