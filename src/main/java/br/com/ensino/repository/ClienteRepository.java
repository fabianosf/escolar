package br.com.ensino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ensino.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
}
