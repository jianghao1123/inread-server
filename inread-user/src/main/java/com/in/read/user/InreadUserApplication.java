package com.in.read.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.in.read.user.mapper")
public class InreadUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(InreadUserApplication.class, args);
	}

}

