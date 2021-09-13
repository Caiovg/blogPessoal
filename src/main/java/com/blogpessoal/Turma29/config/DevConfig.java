package com.blogpessoal.Turma29.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.blogpessoal.Turma29.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService service;
	
	@Bean
	public void instanciaDB() {	
		this.service.instanceDB();
	}
}
