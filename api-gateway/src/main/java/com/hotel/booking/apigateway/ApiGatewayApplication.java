package com.hotel.booking.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
/**
 * The main class for the API Gateway application.
 */
public class ApiGatewayApplication {

	/**
	 * The entry point of the API Gateway application.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
