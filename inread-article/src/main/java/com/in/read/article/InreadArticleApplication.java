package com.in.read.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.in.read")
public class InreadArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(InreadArticleApplication.class, args);
	}

}

