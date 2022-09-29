package com.cts.training.digitalbooknotificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DigitalbookNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalbookNotificationServiceApplication.class, args);
	}

}
