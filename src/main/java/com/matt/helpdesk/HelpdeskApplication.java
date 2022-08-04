package com.matt.helpdesk;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matt.helpdesk.domain.Chamado;
import com.matt.helpdesk.domain.Cliente;
import com.matt.helpdesk.domain.Tecnico;
import com.matt.helpdesk.domain.enums.Perfil;
import com.matt.helpdesk.domain.enums.Prioridade;
import com.matt.helpdesk.domain.enums.Status;
import com.matt.helpdesk.repositories.ChamadoRepository;
import com.matt.helpdesk.repositories.ClienteRepository;
import com.matt.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Matheus Brito", "04478389110", "matt@email.com", "123456");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "04455522213", "torvalds@email.com", "123456");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Primeiro Chamado", "Chamado 1", tec1, cli1);
		
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}
