package org.example;

import org.example.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.model.*;
import org.example.model.DTO.*;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    // @Bean
    public CommandLineRunner commandLineRunner(
            CustomerService customerService
    ) {
        return args -> {
            for (int i = 0; i < 50; i++) {
               customerService.create(Customer.builder()
                               .firstName("Ivan " + i)
                               .lastName("Stoyanov " + i)
                               .age(i)
                       .build());
            }
        };
    }
}