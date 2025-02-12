package com.example.ConnectionService.service;

import com.example.ConnectionService.dto.ConnectionDTO;
import com.example.ConnectionService.entity.ConnectionEntity;
import com.example.ConnectionService.entity.ConnectionStatus;
import com.example.ConnectionService.repo.ConnectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConnectionService {

    private final ConnectionRepository connectionRepository;

    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    private ConnectionDTO convertToDTO(ConnectionEntity connection) {
        ConnectionDTO dto = new ConnectionDTO();
        dto.setId(connection.getId());
        dto.setSenderId(connection.getSenderId());
        dto.setReceiverId(connection.getReceiverId());
        dto.setStatus(connection.getStatus());
        return dto;
    }

    public List<ConnectionDTO> getPendingRequests(Long userId) {
        return connectionRepository.findByReceiverIdAndStatus(userId, ConnectionStatus.PENDING)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ConnectionDTO> getConnections(Long userId) {
        return connectionRepository.findBySenderIdAndStatus(userId, ConnectionStatus.ACCEPTED)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConnectionDTO sendConnectionRequest(Long senderId, Long receiverId) {
        Optional<ConnectionEntity> existingConnection = connectionRepository.findBySenderIdAndReceiverId(senderId, receiverId);
        if (existingConnection.isPresent()) {
            throw new RuntimeException("Connection request already exists!");
        }

        ConnectionEntity connection = new ConnectionEntity();
        connection.setSenderId(senderId);
        connection.setReceiverId(receiverId);
        connection.setStatus(ConnectionStatus.PENDING);
        return convertToDTO(connectionRepository.save(connection));
    }

    @Transactional
    public ConnectionDTO acceptConnection(Long requestId) {
        ConnectionEntity connection = connectionRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Connection request not found!"));

        connection.setStatus(ConnectionStatus.ACCEPTED);
        return convertToDTO(connectionRepository.save(connection));
    }

    @Transactional
    public void removeConnection(Long connectionId) {
        if (!connectionRepository.existsById(connectionId)) {
            throw new RuntimeException("Connection not found!");
        }
        connectionRepository.deleteById(connectionId);
    }
}
