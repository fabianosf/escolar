package br.com.ensino.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ensino.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
}
