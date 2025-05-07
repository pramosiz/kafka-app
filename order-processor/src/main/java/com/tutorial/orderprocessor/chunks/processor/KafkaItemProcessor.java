package com.tutorial.orderprocessor.chunks.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.tutorial.orderprocessor.models.Order;

@Component
public class KafkaItemProcessor implements ItemProcessor<String, Order> {

    @Override
    public Order process(String orderString) throws Exception {
        System.out.println("Processing order: " + orderString);
        Gson gson = new Gson();
        Order order = gson.fromJson(orderString, Order.class);
        // order.setAmount(order.getAmount() * 1.1);
        order.setLastUpdate(new java.sql.Timestamp(System.currentTimeMillis()));
        return order;
    }

}
