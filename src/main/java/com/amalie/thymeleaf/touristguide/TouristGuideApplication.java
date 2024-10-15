package com.amalie.thymeleaf.touristguide;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TouristGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(TouristGuideApplication.class, args);
    }

}
