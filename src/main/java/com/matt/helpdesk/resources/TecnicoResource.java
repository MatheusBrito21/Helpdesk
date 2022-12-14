package com.matt.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matt.helpdesk.domain.Tecnico;
import com.matt.helpdesk.domain.dtos.TecnicoDTO;
import com.matt.helpdesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> obterPorId(@PathVariable Integer id){
		Tecnico tecnico = service.obterPorId(id);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
		
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> obterTodos(){
		List<Tecnico> lista = service.obterTodos();
		List<TecnicoDTO> listDTO = lista.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TecnicoDTO> addTecnico(@Valid @RequestBody TecnicoDTO objDTO){
		Tecnico novoTec = service.addTecnico(objDTO);
		//retorna na url o id do objeto criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoTec.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(path = "/{id}")
	public ResponseEntity<TecnicoDTO> updateTecnico(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
		Tecnico updateTec = service.updateTecnico(id,objDTO);
		return ResponseEntity.ok().body(new TecnicoDTO(updateTec));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<TecnicoDTO> deleteTecnico(@PathVariable Integer id){
		service.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	
		
}
