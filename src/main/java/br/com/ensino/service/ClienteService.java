package br.com.ensino.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ensino.model.Cliente;
import br.com.ensino.repository.ClienteRepository;

@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Cliente save(Cliente cliente) {
		if (cliente.getId() != null && clienteRepository.exists(cliente.getId())) {
			throw new EntityExistsException("Este cliente ja existe na base de dados");
		}
		return clienteRepository.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		if (cliente.getId() != null && !clienteRepository.exists(cliente.getId())) {
			throw new EntityNotFoundException("Nao existe cliente com o id no banco de dados");
		}
		return clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findOne(Integer id) {
		return clienteRepository.findOne(id);
	}

	public void delete(Integer id) {
		clienteRepository.delete(id);
	}

}
