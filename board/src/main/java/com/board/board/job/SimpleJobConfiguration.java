package com.board.board.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class SimpleJobConfiguration {

    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob(){
        return jobBuilderFactory.get("simpleJob")// batch job 생성
                .start(simpleStep1())
                .build();
    }

    @Bean
    public Step simpleStep1(){
        return stepBuilderFactory.get("simpleStep1") //batch step 생성
                .tasklet(((stepContribution, chunkContext) -> { //step 안에서 수행될 기능들 명시
                    log.info(">>>> This is Step1");
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}
