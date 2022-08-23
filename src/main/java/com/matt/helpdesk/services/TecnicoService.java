package com.matt.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matt.helpdesk.domain.Pessoa;
import com.matt.helpdesk.domain.Tecnico;
import com.matt.helpdesk.domain.dtos.TecnicoDTO;
import com.matt.helpdesk.repositories.PessoaRepository;
import com.matt.helpdesk.repositories.TecnicoRepository;
import com.matt.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.matt.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico obterPorId(Integer id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!Id "+id));
	}

	public List<Tecnico> obterTodos() {
		return tecnicoRepository.findAll();
	}

	public Tecnico addTecnico(TecnicoDTO objDTO) {
		objDTO.setId(null);
		validaCPFeEmail(objDTO);
		Tecnico tec = new Tecnico(objDTO);
		return tecnicoRepository.save(tec);
	}

	private void validaCPFeEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> objCpf = pessoaRepository.findByCpf(objDTO.getCpf());
		if(objCpf.isPresent() && objCpf.get().getId()!= objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
		}
		
		Optional<Pessoa> obj_Email = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj_Email.isPresent() && obj_Email.get().getId()!= objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
		}
	}
}
