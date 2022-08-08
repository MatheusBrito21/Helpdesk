package com.matt.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.matt.helpdesk.services.DBService;

//essa classe chama automaticamente o metodo instanciaDB da classe DBService
//passando algumas instancias para teste quando o perfil test estiver ativado
//no arquivo application-test.properties

@Configuration
@Profile("test")
public class TesteConfig {

	@Autowired
	private DBService dbService;

    @Bean
    void instanciaDB() {
        this.dbService.instanciaDB();
    }
	
}
