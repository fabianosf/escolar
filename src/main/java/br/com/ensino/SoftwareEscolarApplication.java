package br.com.ensino;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ensino.model.Cliente;
import br.com.ensino.model.Pedido;
import br.com.ensino.repository.ClienteRepository;
import br.com.ensino.repository.PedidoRepository;

@SpringBootApplication
public class SoftwareEscolarApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SoftwareEscolarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Pedido p = new Pedido();
		p.setEnderecoEntrega("Rua da Ajuda");
		p.setConfirmar(true);
		p.setPreco(20.00);
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos.add(p);
		
		Cliente c = new Cliente();
		c.setLogin("Pedrao");
		c.setNome("Pedro Rocha");
		c.setSenha("123");
		c.setCpf("123.123.123-45");	
		 
		
		//pedidoRepository.save(p);
		//clienteRepository.save(c);
		
		
		
		
		
		
		

	}

}
