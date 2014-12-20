package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.app.AddCalculator;
import com.example.app.ArgumentResolver;
import com.example.app.Calculator;
import com.example.app.Frontend;
import com.example.app.ScannerArgumentResolver;

@Configuration
public class AppConfig {
	@Bean
	Calculator calculator() {
		return new AddCalculator();
	}
	@Bean
	ArgumentResolver argumentResolver() {
		return new ScannerArgumentResolver();
	}
	@Bean
	Frontend frontend() {
		return new Frontend();
	}
}
