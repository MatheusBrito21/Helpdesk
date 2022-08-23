package com.matt.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matt.helpdesk.domain.Tecnico;
import com.matt.helpdesk.repositories.TecnicoRepository;
import com.matt.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico obterPorId(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!Id "+id));
	}

	public List<Tecnico> obterTodos() {
		return tecnicoRepository.findAll();
	}
}
