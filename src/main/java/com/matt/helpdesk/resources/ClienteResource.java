package com.matt.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matt.helpdesk.domain.Cliente;
import com.matt.helpdesk.domain.dtos.ClienteDTO;
import com.matt.helpdesk.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	ClienteService service;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ClienteDTO> obterPorId(@PathVariable Integer id){
		Cliente obj = service.obterPorId(id);
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> obterTodos(){
		List<Cliente> listaCliente = service.obterTodos();
		List<ClienteDTO> listaDTO = listaCliente.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> inserirCliente(@Valid @RequestBody ClienteDTO objDTO){
		Cliente novoCliente = service.inserirCliente(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoCliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
