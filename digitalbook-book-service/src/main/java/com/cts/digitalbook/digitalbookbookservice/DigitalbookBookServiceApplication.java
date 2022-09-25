package com.cts.digitalbook.digitalbookbookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DigitalbookBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalbookBookServiceApplication.class, args);
	}

}
