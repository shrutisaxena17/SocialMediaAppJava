package com.example.ConnectionService.controller;

import com.example.ConnectionService.dto.ConnectionDTO;
import com.example.ConnectionService.service.ConnectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{receiverId}")
    public ResponseEntity<ConnectionDTO> sendConnectionRequest(@RequestParam String senderId, @PathVariable String receiverId) {
        ConnectionDTO connection = connectionService.sendConnectionRequest(senderId, receiverId);
        return ResponseEntity.status(201).body(connection);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{requestId}/accept")
    public ResponseEntity<ConnectionDTO> acceptConnection(@PathVariable Long requestId) {
        ConnectionDTO connection = connectionService.acceptConnection(requestId);
        return ResponseEntity.ok(connection);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/{connectionId}")
    public ResponseEntity<Void> removeConnection(@PathVariable Long connectionId) {
        connectionService.removeConnection(connectionId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{userId}/pending")
    public ResponseEntity<List<ConnectionDTO>> getPendingRequests(@PathVariable String userId) {
        return ResponseEntity.ok(connectionService.getPendingRequests(userId));
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{userId}/connections")
    public ResponseEntity<List<ConnectionDTO>> getConnections(@PathVariable String userId) {
        return ResponseEntity.ok(connectionService.getConnections(userId));
    }
}
