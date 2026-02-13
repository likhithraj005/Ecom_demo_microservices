package com.likhith.microservices.eurekam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekamServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekamServerApplication.class, args);
	}

}
