package com.matt.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matt.helpdesk.domain.Pessoa;
import com.matt.helpdesk.domain.Tecnico;
import com.matt.helpdesk.domain.dtos.TecnicoDTO;
import com.matt.helpdesk.repositories.PessoaRepository;
import com.matt.helpdesk.repositories.TecnicoRepository;
import com.matt.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.matt.helpdesk.services.exceptions.ObjectNotFoundException;

import net.bytebuddy.implementation.bytecode.Throw;

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
	
	public Tecnico updateTecnico(Integer id ,@Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico updateTec = obterPorId(id);
		validaCPFeEmail(objDTO);
		updateTec = new Tecnico(objDTO);
		return tecnicoRepository.save(updateTec);
	}
	
	public void deletarPorId(Integer id) {
		Tecnico obj = obterPorId(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O tecnico possui chamados abertos e nao pode ser deletado");
		}
		tecnicoRepository.deleteById(id);
	}
	
	private void validaCPFeEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId()!= objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
		}
		
		 obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId()!= objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
		}
	}

	


}
