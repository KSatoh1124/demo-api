package com.example.demo.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) 
	{
		registry
		.addMapping("/**")
		.allowedMethods("GET", "POST", "PATCH", "DELETE")
		.allowedOrigins("http://localhost:8080");
	}
}
