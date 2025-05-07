package com.tutorial.orderprocessor.chunks.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.tutorial.orderprocessor.models.Order;
import com.tutorial.orderprocessor.services.OrderService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaItemWriter implements ItemWriter<Order> {

    private final OrderService orderService;

    @Override
    public void write(Chunk<? extends Order> chunk) throws Exception {
        chunk.forEach(record -> {
            // Assuming record is a String representation of the order
            // You can parse it if needed
            System.out.println("Writing order to database: " + record);
            orderService.processOrder(record);
        });
    }
}
