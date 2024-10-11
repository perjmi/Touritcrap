package com.amalie.thymeleaf.touristguide;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TouristGuideApplication {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(TouristGuideApplication.class, args);
    }

    @PostConstruct
    public void printEnvVariables() {
        System.out.println("Database URL: " + dbUrl);
        System.out.println("Database Username: " + username);
        System.out.println("Database Password: " + password);
    }
}
