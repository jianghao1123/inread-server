package com.in.read.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.in.read")
@MapperScan("com.in.read.article.mapper")
public class InreadArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(InreadArticleApplication.class, args);
	}

}

