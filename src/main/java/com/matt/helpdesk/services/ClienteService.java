package com.matt.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Cliente obterPorId(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
	}

	public List<Cliente> obterTodos() {
		return clienteRepository.findAll();
	}

	public Cliente inserirCliente(@Valid ClienteDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaCPFeEmail(objDTO);
		Cliente novoCliente = new Cliente(objDTO);
		return clienteRepository.save(novoCliente);
	}
	
	public Cliente updateCliente(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente update = obterPorId(id);
		validaCPFeEmail(objDTO);
		update = new Cliente(objDTO);
		update.setDataCriacao(objDTO.getDataCriacao());
		return clienteRepository.save(update);
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

	public void deletarPorId(Integer id) {
		Cliente clienteDel = obterPorId(id);
		if(clienteDel.getChamados().size()>0) {
			throw new DataIntegrityViolationException("O cliente possui chamados em aberto!");
		}
		clienteRepository.deleteById(id);
	}


	
	
	
}
