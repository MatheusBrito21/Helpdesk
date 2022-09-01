package com.matt.helpdesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matt.helpdesk.domain.Chamado;
import com.matt.helpdesk.domain.Cliente;
import com.matt.helpdesk.domain.Tecnico;
import com.matt.helpdesk.domain.dtos.ChamadoDTO;
import com.matt.helpdesk.domain.enums.Prioridade;
import com.matt.helpdesk.domain.enums.Status;
import com.matt.helpdesk.repositories.ChamadoRepository;
import com.matt.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
	@Autowired
	ChamadoRepository chamadoRepository;
	@Autowired
	ClienteService clienteService;
	@Autowired
	TecnicoService tecnicoService;
	
	public Chamado obterPorId(Integer id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado!"));
	}

	public List<Chamado> obterTodos() {
		return chamadoRepository.findAll();
	}

	public Chamado createChamado(@Valid ChamadoDTO objDTO) {
		return chamadoRepository.save(novoChamado(objDTO));
	}
	
	public Chamado updateChamado(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado chamado = obterPorId(id);
		chamado = novoChamado(objDTO);
		return chamadoRepository.save(chamado);
	}
	
	private Chamado novoChamado(ChamadoDTO obj) {
		Tecnico tec = tecnicoService.obterPorId(obj.getTecnico());
		Cliente cli = clienteService.obterPorId(obj.getCliente());
		//caso o id do obj nao seja nulo significa que esta havendo um atualização de um chamado existente
		Chamado chamado = new Chamado();
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		chamado.setTitulo(obj.getTitulo());
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setObservacoes(obj.getObservacoes());
		chamado.setDataAbertura(obj.getDataAbertura());
		chamado.setCliente(cli);
		chamado.setTecnico(tec);
		
		return chamado;
	}

	

}
