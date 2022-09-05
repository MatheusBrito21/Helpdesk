package com.matt.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.matt.helpdesk.domain.Chamado;
import com.matt.helpdesk.domain.Cliente;
import com.matt.helpdesk.domain.Tecnico;
import com.matt.helpdesk.domain.enums.Perfil;
import com.matt.helpdesk.domain.enums.Prioridade;
import com.matt.helpdesk.domain.enums.Status;
import com.matt.helpdesk.repositories.ChamadoRepository;
import com.matt.helpdesk.repositories.ClienteRepository;
import com.matt.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public void instanciaDB(){
		Tecnico tec1 = new Tecnico(null, "Matheus Brito", "04478389110", "matt@email.com", encoder.encode("123456"));
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvalds", "04455522213", "torvalds@email.com", encoder.encode("123456"));
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Primeiro Chamado", "Chamado 1", tec1, cli1);
		
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
