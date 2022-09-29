package com.masantello.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.masantello.demo.services.DBService;

@Configuration
@Profile("Dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;
	
	@Bean
	public boolean populaBD() {
		if (ddl.equalsIgnoreCase("create")) {			
			this.dbService.populaBD();
		}
		return false;
	}
	
}
