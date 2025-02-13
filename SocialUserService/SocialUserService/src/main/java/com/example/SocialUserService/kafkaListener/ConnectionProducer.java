package com.example.SocialUserService.kafkaListener;

import com.example.SocialUserService.dto.ConnectionEventDTO;
import com.example.SocialUserService.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class ConnectionProducer {

    @Autowired
    UserProfileService userProfileService;

    @KafkaListener(topics = "connection_updates",groupId = "social_user_service")
    public void consumeConnectionEvent(ConnectionEventDTO event) {
        if ("ACCEPTED".equals(event.getAction())) {
            userProfileService.updateFollowerFollowingCount(event.getReceiverId(), event.getSenderId(), 1);
        } else if ("REMOVED".equals(event.getAction())) {
            userProfileService.updateFollowerFollowingCount(event.getReceiverId(), event.getSenderId(), -1);
        }
    }
}
