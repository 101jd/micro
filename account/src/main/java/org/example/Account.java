package org.example;

import org.example.model.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Account {

    @Bean
    public Order order(){
        return new Order();
    }

    public static void main(String[] args) {
        SpringApplication.run(Account.class, args);
    }

}