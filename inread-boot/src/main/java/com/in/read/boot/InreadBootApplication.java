package com.in.read.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by luyun on 2019/1/5.
 */
@SpringBootApplication
@ComponentScan("com.in.read")
@MapperScan("com.in.read.*.mapper")
public class InreadBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(InreadBootApplication.class, args);
    }
}
