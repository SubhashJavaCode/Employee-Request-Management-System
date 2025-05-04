package com.fuji.erms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fuji")
public class EmployeeRequestManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRequestManagementSystemApplication.class,
				args);
	}

}
