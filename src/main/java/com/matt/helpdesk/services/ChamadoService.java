package com.matt.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matt.helpdesk.domain.Chamado;
import com.matt.helpdesk.repositories.ChamadoRepository;
import com.matt.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	ChamadoRepository chamadoRepository;
	
	public Chamado obterPorId(Integer id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado!"));
	}

}
