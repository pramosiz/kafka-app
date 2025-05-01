package com.tutorial.orderproducer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tutorial.orderproducer.models.Order;
import com.tutorial.orderproducer.service.OrderService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        Gson gson = new Gson();
        String orderJson = gson.toJson(order);
        orderService.sendOrder(orderJson);
        String message = "Order sent to Kafka: " + orderJson;
        System.out.println(message);
        return message;
    }

}
