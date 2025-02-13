package com.example.ConnectionService.repo;

import com.example.ConnectionService.entity.ConnectionEntity;
import com.example.ConnectionService.entity.ConnectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository extends JpaRepository<ConnectionEntity, Long> {

    List<ConnectionEntity> findByReceiverIdAndStatus(String receiverId, ConnectionStatus status);

    List<ConnectionEntity> findBySenderIdAndStatus(String senderId, ConnectionStatus status);

    Optional<ConnectionEntity> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
