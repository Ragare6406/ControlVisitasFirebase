package com.rgarcia.controlvisitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.rgarcia.controlvisitas")
public class ControlvisitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlvisitasApplication.class, args);
	}

}
