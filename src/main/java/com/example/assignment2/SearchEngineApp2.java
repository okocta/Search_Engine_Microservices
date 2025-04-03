package com.example.assignment2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SearchEngineApp2 implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SearchEngineApp2.class, args);
    }

    @Override
    public void run(String[] args) {
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
