package com.tutorial.orderprocessor.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.tutorial.orderprocessor.services.OrderService;

@Component
public class OrderConsumer {

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "orders", groupId = "consumer1", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String orderString) {
        orderService.processOrder(orderString);
    }
}
