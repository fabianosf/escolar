package br.com.ensino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ensino.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
	
	 

}
