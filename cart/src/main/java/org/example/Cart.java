package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Cart {

    @Bean
    public HttpHeaders headers(){
        return new HttpHeaders();
    }

    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Cart.class, args);
    }

}
