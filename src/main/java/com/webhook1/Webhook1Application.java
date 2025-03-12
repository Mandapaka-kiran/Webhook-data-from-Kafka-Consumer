package com.webhook1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class Webhook1Application {

	public static void main(String[] args) {
		SpringApplication.run(Webhook1Application.class, args);
	}

}
