package com.masantello.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.masantello.demo.services.DatabaseService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DatabaseService dbService;
	
	@Bean
	public void populateDB() {
		this.dbService.populateDB();
	}
}
