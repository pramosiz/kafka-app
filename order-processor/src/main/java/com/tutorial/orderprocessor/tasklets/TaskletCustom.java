package com.tutorial.orderprocessor.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class TaskletCustom {

    public Tasklet firstTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println();
                System.out.println("Executing first tasklet\n");
                return RepeatStatus.FINISHED;
            }
        };
    }

    public Tasklet firstTasklet_Alternative() {
        return (contribution, chunkContext) -> {
            System.out.println("Executing first tasklet");
            return RepeatStatus.FINISHED;
        };
    }

    public Tasklet secondTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println();
                System.out.println("Executing second tasklet\n");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
