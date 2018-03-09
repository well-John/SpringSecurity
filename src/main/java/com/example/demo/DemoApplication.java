package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();

        System.out.println("所以beanNames个数：" + beanNames.length);

        for (String bn : beanNames) {

            System.out.println(bn);

        }

    }
}
