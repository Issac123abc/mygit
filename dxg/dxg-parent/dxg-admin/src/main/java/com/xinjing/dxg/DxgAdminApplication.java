package com.xinjing.dxg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DxgAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(DxgAdminApplication.class, args);
	}

}
