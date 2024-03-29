package com.venkat.myspringjpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringJpaApplication.class, args);
	}

	@Bean
	public Faker faker(){
		return new Faker();
	}
	/*@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			System.out.println("Command Line Runner...");
		};
	}*/
}

