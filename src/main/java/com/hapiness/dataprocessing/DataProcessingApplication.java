package com.hapiness.dataprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DataProcessingApplication implements WebMvcConfigurer {

    //Please make sure you read fist ReadMe.md file before running application

    public static void main(String[] args) {
        SpringApplication.run(DataProcessingApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
    }

}
