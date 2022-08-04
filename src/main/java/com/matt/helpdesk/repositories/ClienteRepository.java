package com.matt.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matt.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
