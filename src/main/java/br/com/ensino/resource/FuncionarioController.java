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

import br.com.ensino.model.Funcionario;
import br.com.ensino.service.FuncionarioService;

@RestController
@RequestMapping("/api")
public class FuncionarioController {

	private FuncionarioService funcionarioService;

	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@RequestMapping(value = "funcionario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Funcionario> getAllFuncionarios() {
		return funcionarioService.findAll();
	}

	@RequestMapping(value = "funcionario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario)
			throws URISyntaxException {
		try {
			Funcionario result = funcionarioService.save(funcionario);
			return ResponseEntity.created(new URI("/api/funcionario/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Funcionario>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "funcionario", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Funcionario> updateFuncionario(@RequestBody Funcionario funcionario)
			throws URISyntaxException {
		if (funcionario.getId() == null) {
			return new ResponseEntity<Funcionario>(HttpStatus.NOT_FOUND);
		}

		try {
			Funcionario result = funcionarioService.update(funcionario);

			return ResponseEntity.created(new URI("/api/funcionario/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Funcionario>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteFuncionario(@PathVariable Integer id) {
		funcionarioService.delete(id);

		return ResponseEntity.ok().build();
	}

}
