package com.xinjing.dxg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DxgUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DxgUserApplication.class, args);
	}

}
