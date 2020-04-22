package com.xinjing.dxg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DxgEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DxgEurekaApplication.class, args);
	}

}
