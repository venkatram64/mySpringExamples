package com.venkat.post;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyPostAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPostAppApplication.class, args);
	}

	@Bean
	public Faker faker(){
		return new Faker();
	}

}
