package com.venkat.myapp;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;

@SpringBootApplication
@EnableScheduling
public class StandaloneAppApplication {

	private Logger logger = LoggerFactory.getLogger(StandaloneAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StandaloneAppApplication.class, args);
	}

	@Bean
	public Faker faker(){
		return new Faker();
	}

	//@Scheduled(fixedRate = 2000L)
	public void job() throws InterruptedException {
		logger.info("Running the job at fixed rate " + LocalDateTime.now());
		Thread.sleep(1000);
	}

	//@Scheduled(fixedDelay = 2000L)//waits for completion time of method execution
	public void job2() throws InterruptedException {
		logger.info("Running the job at at fixed delay " + LocalDateTime.now());
		Thread.sleep(1000);
	}

	//@Scheduled(fixedDelayString = "PT02S")// P=Period, T=Time, 2S
	public void job3() throws InterruptedException {
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = current.format(format);
		logger.info("Running the job at at fixed delay String " + formattedDateTime);
		Thread.sleep(1000);
	}

	//* seconds, * minutes, * hours, * day of the month, * month, * day of the week
	//for every 2 minutes *0 */2 * * * *  for every 2 minutes
	//for every day at 8PM    "0 0 20 * * *
	//for weekly at 8PM on MONDAY "0 0 20 * * MON"
	@Scheduled(cron="*/2 * * * * *")// runs every 2 seconds, cron is single threaded
	public void job4() throws InterruptedException {
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = current.format(format);
		logger.info("Running the job4 at at cron job " + formattedDateTime);
		Thread.sleep(1000);
	}

	@Scheduled(cron="*/2 * * * * *")// runs every 2 seconds
	public void job5() throws InterruptedException {
		logger.info("Running the job5 at at cron job " + LocalDateTime.now());
		Thread.sleep(1000);
	}

}
