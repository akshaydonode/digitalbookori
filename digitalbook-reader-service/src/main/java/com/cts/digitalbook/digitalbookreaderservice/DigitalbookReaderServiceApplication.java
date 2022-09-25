package com.cts.digitalbook.digitalbookreaderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DigitalbookReaderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalbookReaderServiceApplication.class, args);
	}

}
