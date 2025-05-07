package com.tutorial.orderprocessor.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.tutorial.orderprocessor.chunks.processor.KafkaItemProcessor;
import com.tutorial.orderprocessor.chunks.reader.KafkaItemReader;
import com.tutorial.orderprocessor.chunks.writer.KafkaItemWriter;
import com.tutorial.orderprocessor.models.Order;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final KafkaItemReader kafkaItemReader;
    private final KafkaItemProcessor kafkaItemProcessor;
    private final KafkaItemWriter kafkaItemWriter;

    @Bean
    public Job kafkaJob() {
        return new JobBuilder("KafkaJob", jobRepository)
                .start(kafkaStep())
                .build();
    }

    @Bean
    public Step kafkaStep() {
        return new StepBuilder("kafkaStep", jobRepository)
                .<String, Order>chunk(2, transactionManager)
                .reader(kafkaItemReader) // Indicamos que la lectura es de tipo String
                .processor(kafkaItemProcessor) // Indicamos que el procesamiento es de tipo String y la salida de tipo
                                               // Order
                .writer(kafkaItemWriter) // Indicamos que la escritura es de tipo Order
                .build();
    }
}
