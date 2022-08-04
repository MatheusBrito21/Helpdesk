package com.matt.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matt.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
