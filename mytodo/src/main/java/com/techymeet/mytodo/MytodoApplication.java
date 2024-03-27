package com.techymeet.mytodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.techymeet")
public class MytodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MytodoApplication.class, args);
	}

}
