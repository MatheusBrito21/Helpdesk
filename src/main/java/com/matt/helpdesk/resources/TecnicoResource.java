package com.matt.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		
}
