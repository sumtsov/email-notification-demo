package com.dsumtsov.email.notification.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EmailNotificationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailNotificationDemoApplication.class, args);
	}

}
