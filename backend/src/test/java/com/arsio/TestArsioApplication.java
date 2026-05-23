package com.arsio;

import org.springframework.boot.SpringApplication;

public class TestArsioApplication {

	public static void main(String[] args) {
		SpringApplication.from(ArsioApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
