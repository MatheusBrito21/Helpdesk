package com.matt.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matt.helpdesk.domain.Cliente;
import com.matt.helpdesk.domain.Pessoa;
import com.matt.helpdesk.domain.dtos.ClienteDTO;
import com.matt.helpdesk.repositories.ClienteRepository;
import com.matt.helpdesk.repositories.PessoaRepository;
import com.matt.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.matt.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;

	public Cliente obterPorId(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
	}

	public List<Cliente> obterTodos() {
		return clienteRepository.findAll();
	}

	public Cliente inserirCliente(@Valid ClienteDTO objDTO) {
		objDTO.setId(null);
		validaCPFeEmail(objDTO);
		Cliente novoCliente = new Cliente(objDTO);
		return clienteRepository.save(novoCliente);
	}

	private void validaCPFeEmail(@Valid ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId()!= objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado!");
		}
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId()!= objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado!");
		}
	}
	
	
	
}
