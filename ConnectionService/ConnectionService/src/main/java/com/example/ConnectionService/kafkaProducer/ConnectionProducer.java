package com.example.ConnectionService.kafkaProducer;

import com.example.ConnectionService.dto.ConnectionEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConnectionProducer {
    private final KafkaTemplate<String, ConnectionEventDTO> kafkaTemplate;

    public ConnectionProducer(KafkaTemplate<String, ConnectionEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendConnectionEvent(ConnectionEventDTO event) {
        kafkaTemplate.send("connection_updates", event);
        System.out.println("Connection update sent!!"+event.toString());
    }
}
