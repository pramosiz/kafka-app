package com.tutorial.orderprocessor.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.tutorial.orderprocessor.tasklets.TaskletCustom;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SampleJob {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final TaskletCustom taskletCustom;

    @Bean
    public Job firstJob() {
        return new JobBuilder("FirstJobTest", jobRepository)
                .start(firstStep())
                .next(secondStep())
                .build();
    }

    private Step firstStep() {
        return new StepBuilder("FirstStepTest", jobRepository)
                .tasklet(taskletCustom.firstTasklet(), transactionManager)
                .build();
    }

    private Step secondStep() {
        return new StepBuilder("SecondStepTest", jobRepository)
                .tasklet(taskletCustom.secondTasklet(), transactionManager)
                .build();
    }

}
