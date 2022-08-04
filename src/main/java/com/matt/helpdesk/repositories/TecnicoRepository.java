package com.matt.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matt.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}
