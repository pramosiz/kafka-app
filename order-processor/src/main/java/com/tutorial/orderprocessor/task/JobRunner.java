package com.tutorial.orderprocessor.task;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class JobRunner {

    private final JobLauncher jobLauncher; // JobLauncher no permite que un Job con el mismo nombre se ejecute en
                                           // paralelo si ya hay una instancia en ejecución
    private final Job firstJob;
    private final Job kafkaJob;

    @Scheduled(fixedRate = 3600000) // Schedule the job to run every 1 hour
    public void runJob() throws Exception {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("timestamp", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(firstJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 1800000) // Schedule the job to run every 30 minutes
    public void runSecondJob() throws Exception {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("timestamp", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(kafkaJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
