package com.matt.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matt.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
