package com.zc.zuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulgatewayApplication.class, args);
	}
	
	@Bean
	public SimpleFilter preFilter() {
		return new SimpleFilter();
	}
}
