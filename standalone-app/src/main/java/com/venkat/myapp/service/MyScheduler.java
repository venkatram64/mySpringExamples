package com.venkat.myapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
@EnableAsync //to run parallel
public class MyScheduler {

    @Async
    @Scheduled(fixedDelayString = "PT02S")// P=Period, T=Time, 2S
    public void job() throws InterruptedException {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = current.format(format);
        log.info("Running the job at at fixed delay String async " + formattedDateTime);
        Thread.sleep(1000);
    }

    //for weekly at 8PM on MONDAY "0 0 20 * * MON"
    @Scheduled(cron="${cron.expression.value}")
    public void job2() throws InterruptedException {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = current.format(format);
        log.info("Running  cron job " + formattedDateTime);
        Thread.sleep(1000);
    }

    //for weekly at 8PM on MONDAY "0 0 20 * * MON"
    @Scheduled(cron="${cron.expression.value2}")
    public void job3() throws InterruptedException {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = current.format(format);
        log.info("Running  cron job " + formattedDateTime);
        Thread.sleep(1000);
    }
}
