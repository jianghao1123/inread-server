package com.in.read.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by luyun on 2019/1/5.
 */
@SpringBootApplication
@ComponentScan("com.in.read")
@EnableCaching
public class InreadBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(InreadBootApplication.class, args);
    }
}
