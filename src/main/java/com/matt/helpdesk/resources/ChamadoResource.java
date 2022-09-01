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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matt.helpdesk.domain.Chamado;
import com.matt.helpdesk.domain.dtos.ChamadoDTO;
import com.matt.helpdesk.services.ChamadoService;

@RestController
@RequestMapping("/chamado")
public class ChamadoResource {
	
	@Autowired
	ChamadoService service;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ChamadoDTO> obterPorId(@PathVariable Integer id){
		Chamado obj = service.obterPorId(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> obterTodos(){
		List<Chamado> listaObj = service.obterTodos();
		List<ChamadoDTO> listaDTO = listaObj.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@PostMapping
	public ResponseEntity<ChamadoDTO> createChamado(@Valid @RequestBody ChamadoDTO objDTO){
		Chamado chamado = service.createChamado(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<ChamadoDTO> updateChamado(@PathVariable Integer id, @RequestBody @Valid ChamadoDTO objDTO){
		Chamado upChamado = service.updateChamado(id,objDTO);
		return ResponseEntity.ok().body(new ChamadoDTO(upChamado));
	}
	
}
