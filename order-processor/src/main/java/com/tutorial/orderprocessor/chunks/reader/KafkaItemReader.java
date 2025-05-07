package com.tutorial.orderprocessor.chunks.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class KafkaItemReader implements ItemReader<String> {

    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    @KafkaListener(topics = "${topics.orders}", groupId = "test")
    public void listen(String message) {
        queue.offer(message);
        System.out.println("Reading message from Kafka: " + message);
    }

    @Override
    public String read() {
        try {
            String message = queue.poll(1, java.util.concurrent.TimeUnit.MINUTES);
            if (message != null) {
                System.out.println("Reading message from queue: " + message);
            }
            return message;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}
