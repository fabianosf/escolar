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

import br.com.ensino.model.Pedido;
import br.com.ensino.service.PedidoService;

@RestController
@RequestMapping("/api")
public class PedidoController {

	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@RequestMapping(value = "pedido", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Pedido> getAllPedidos() {
		return pedidoService.findAll();
	}

	@RequestMapping(value = "pedido", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) throws URISyntaxException {
		try {
			Pedido result = pedidoService.save(pedido);
			return ResponseEntity.created(new URI("/api/pedido/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Pedido>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "pedido", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido) throws URISyntaxException {
		if (pedido.getId() == null) {
			return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
		}

		try {
			Pedido result = pedidoService.update(pedido);

			return ResponseEntity.created(new URI("/api/pedido/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/pedido/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deletePedido(@PathVariable Integer id) {
		pedidoService.delete(id);

		return ResponseEntity.ok().build();
	}

}
