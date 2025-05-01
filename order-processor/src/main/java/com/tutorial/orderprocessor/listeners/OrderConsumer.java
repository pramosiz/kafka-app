package com.tutorial.orderprocessor.listeners;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.tutorial.orderprocessor.models.Order;
import com.tutorial.orderprocessor.repositories.OrderRepository;

@Component
public class OrderConsumer {

    private final List<Order> buffer = new ArrayList<>();

    @Autowired
    private OrderRepository orderRepository;

    @KafkaListener(topics = "orders", groupId = "consumer1", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String orderString) {
        try {
            Gson gson = new Gson();
            Order order = gson.fromJson(orderString, Order.class);
            order.setLastUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
            buffer.add(order);
            orderRepository.save(order);
            System.out.println("Processed order: " + orderString);
        } catch (Exception e) {
            System.err.println("Error processing order: " + e.getMessage());
        }
    }

    public List<Order> getBuffer() {
        return buffer;
    }
}
