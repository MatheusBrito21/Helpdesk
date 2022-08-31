package com.matt.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}