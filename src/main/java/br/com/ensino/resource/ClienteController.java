package br.com.ensino.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ensino.model.Cliente;
import br.com.ensino.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {

	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@RequestMapping(value = "cliente", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> getAllClientes() {
		return clienteService.findAll();
	}

	@RequestMapping(value = "cliente", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) throws URISyntaxException {
		try {
			Cliente result = clienteService.save(cliente);
			return ResponseEntity.created(new URI("/api/cliente/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Cliente>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "cliente", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) throws URISyntaxException {
		if (cliente.getId() == null) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}

		try {
			Cliente result = clienteService.update(cliente);

			return ResponseEntity.created(new URI("/api/cliente/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
		clienteService.delete(id);

		return ResponseEntity.ok().build();
	}

}
