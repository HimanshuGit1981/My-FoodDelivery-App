package com.iris.eatfeat.foodcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration(proxyBeanMethods = false)
public class FoodDeliveryFoodcatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryFoodcatalogueApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		//return new RestTemplate();
		return builder.build();
	}

}
