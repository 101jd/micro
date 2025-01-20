package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import reactor.netty.http.client.HttpClient;


@EnableConfigurationProperties
@SpringBootApplication
public class Gateway {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes().route(r -> r
                        .path("/cart")
                        .filters(f -> f.addRequestHeader("name", "cart"))
                        .uri("http://127.0.0.1:8081"))
                .route(r -> r
                        .path("/inv")
                        .filters(f -> f.addRequestHeader("name", "inventory"))
                        .uri("http://127.0.0.1:8082"))
                .route(r -> r
                        .path("/acc")
                        .filters(f -> f.addRequestHeader("name", "acc"))
                        .uri("http://127.0.0.1:8083"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Gateway.class, args);
    }

}

