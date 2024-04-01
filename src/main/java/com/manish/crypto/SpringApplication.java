package com.manish.crypto;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringApplication {

    public static void main( String []args) {
        ApplicationContext context = org.springframework.boot.SpringApplication.run(SpringBootApplication.class, args);
    }
}
