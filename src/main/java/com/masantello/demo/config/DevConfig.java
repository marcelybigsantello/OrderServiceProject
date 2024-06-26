package com.masantello.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.masantello.demo.services.DatabaseService;

@Configuration
@Profile("Dev")
public class DevConfig {
	
	@Autowired
	private DatabaseService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;
	
	@Bean
	public boolean populateDB() {
		if (ddl.equalsIgnoreCase("create")) {			
			this.dbService.populateDB();
		}
		return false;
	}
	
}
