package com.tutorial.orderproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String topic = "orders";

    public void sendOrder(String orderJson) {
        kafkaTemplate.send(topic, orderJson);
    }
}
