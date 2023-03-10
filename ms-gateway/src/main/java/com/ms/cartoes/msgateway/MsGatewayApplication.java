package com.ms.cartoes.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
					.route(r -> r.path("/clients/**").uri("lb://ms-clientes"))
					.route(r -> r.path("/cards/**").uri("lb://ms-cartoes"))
					.route(r -> r.path("/credit-rating/**").uri("lb://ms-avaliadorcredito"))
					.route(r -> r.path("/card-issuance/**").uri("lb://ms-avaliadorcredito"))
				.build();
	}


}
