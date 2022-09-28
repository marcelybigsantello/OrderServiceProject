package com.masantello.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.masantello.demo.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	//DBService dbService = new DBService();
	
	@Bean
	public void populaBD() {
		this.dbService.populaBD();
	}
}
