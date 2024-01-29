package com.iris.eatfeat.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FoodDeliveryEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryEurekaApplication.class, args);
	}

}
