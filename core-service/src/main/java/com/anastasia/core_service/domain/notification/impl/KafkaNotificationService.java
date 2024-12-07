package com.anastasia.core_service.domain.notification.impl;

import com.anastasia.core_service.domain.notification.NotificationService;
import com.anastasia.trade_project.notification.TradeNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaNotificationService implements NotificationService {

    private final String topic;
    private final KafkaTemplate<String, TradeNotification> kafkaTemplate;

    @Autowired
    public KafkaNotificationService(@Value("${spring.kafka.topic}") String topic,
                                    KafkaTemplate<String, TradeNotification> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
        kafkaTemplate.setDefaultTopic(topic);
    }


    @Override
    public void send(TradeNotification notification) {
        kafkaTemplate.send(topic, notification);
        log.info("Notification is sent to topic '{}'. Message: {}", topic, notification);
    }

    @Override
    public void send(String key, TradeNotification notification) {
        kafkaTemplate.send(topic, key, notification);
        log.info("Notification is sent to topic '{}' with key '{}'. Message: {}", topic, key, notification);
    }
}
