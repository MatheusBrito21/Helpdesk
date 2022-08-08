package com.matt.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matt.helpdesk.services.DBService;

//essa classe chama automaticamente o metodo instanciaDB da classe DBService
//passando algumas instancias para teste quando o perfil test estiver ativado
//no arquivo application-test.properties

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;
	
    @Bean
    void instanciaDB() {
        if(value.equals("create")) {
        	this.dbService.instanciaDB();	
        }
    }
	
}
