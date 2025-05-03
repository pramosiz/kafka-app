package com.tutorial.orderprocessor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tutorial.orderprocessor.models.Order;
import com.tutorial.orderprocessor.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void processOrder(String orderString) {
        try {
            Gson gson = new Gson();
            Order order = gson.fromJson(orderString, Order.class);
            order.setLastUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
            orderRepository.save(order);
            System.out.println("Processed order: " + orderString);
        } catch (Exception e) {
            System.err.println("Error processing order: " + e.getMessage());
        }
    }
}
