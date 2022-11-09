package com.csLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class csLoginApplication {

	public static void main(String[] args) {

		SpringApplication.run(csLoginApplication.class, args);
		System.out.println("auth service is running....................");
	}

}
