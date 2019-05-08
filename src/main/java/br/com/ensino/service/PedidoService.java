package br.com.ensino.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ensino.model.Pedido;
import br.com.ensino.repository.PedidoRepository;

@Service
public class PedidoService {

	private PedidoRepository pedidoRepository;

	@Autowired
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public Pedido save(Pedido pedido) {
		if (pedido.getId() != null && pedidoRepository.exists(pedido.getId())) {
			throw new EntityExistsException("Este pedido ja existe na base de dados");
		}
		return pedidoRepository.save(pedido);
	}

	public Pedido update(Pedido pedido) {
		if (pedido.getId() != null && !pedidoRepository.exists(pedido.getId())) {
			throw new EntityNotFoundException("Nao existe pedido com o id no banco de dados");
		}
		return pedidoRepository.save(pedido);
	}

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	public Pedido findOne(Integer id) {
		return pedidoRepository.findOne(id);
	}

	public void delete(Integer id) {
		pedidoRepository.delete(id);
	}

}
