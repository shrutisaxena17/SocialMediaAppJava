package com.example.ConnectionService.repo;

import com.example.ConnectionService.entity.ConnectionEntity;
import com.example.ConnectionService.entity.ConnectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository extends JpaRepository<ConnectionEntity, Long> {

    List<ConnectionEntity> findByReceiverIdAndStatus(Long receiverId, ConnectionStatus status);

    List<ConnectionEntity> findBySenderIdAndStatus(Long senderId, ConnectionStatus status);

    Optional<ConnectionEntity> findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
