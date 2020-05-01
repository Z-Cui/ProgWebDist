package com.zc.bookmarkdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookmarkdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmarkdbApplication.class, args);
	}

}
