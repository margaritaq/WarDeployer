package com.example.mockClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class MockClientApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MockClientApplication.class, args);
		SheduledTasks scan = new SheduledTasks();
		scan.scanFolder();
	}

}
