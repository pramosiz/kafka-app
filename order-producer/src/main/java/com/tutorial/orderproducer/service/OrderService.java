package com.tutorial.orderproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topics.orders}")
    private String topic;

    public void sendOrder(String orderJson) {
        String dynamicKey = String.valueOf(orderJson.hashCode()); // Generate a dynamic key based on the order JSON for
                                                                  // partitioning
        kafkaTemplate.send(topic, dynamicKey, orderJson);
    }
}
