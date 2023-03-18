package com.mindtree.omf.eserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class EserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EserverApplication.class, args);
	}
}
