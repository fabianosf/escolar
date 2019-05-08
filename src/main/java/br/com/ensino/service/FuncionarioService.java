package br.com.ensino.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ensino.model.Funcionario;
import br.com.ensino.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public Funcionario save(Funcionario funcionario) {
		if(funcionario.getId() != null && funcionarioRepository.exists(funcionario.getId())) {
			throw new EntityExistsException("Este funcionario ja existe na base de dados");
		}
		return funcionarioRepository.save(funcionario);
	}
	
	public Funcionario update(Funcionario funcionario) {
		if(funcionario.getId() != null && !funcionarioRepository.exists(funcionario.getId())) {
			throw new EntityNotFoundException("Nao existe entidade com o id no banco de dados");
		}
		return funcionarioRepository.save(funcionario);
	}
	
	public List<Funcionario> findAll(){
		return funcionarioRepository.findAll();
	}
	
	public Funcionario findOne(Integer id) {
		return funcionarioRepository.findOne(id);
	}
	
	public void delete(Integer id) {
		funcionarioRepository.delete(id);
	}
	
	
	
	
	
	
	
	
}
