package org.utfpr.jparepository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.utfpr.jparepository.entity.Departamento;
import org.utfpr.jparepository.entity.Funcionario;
import org.utfpr.jparepository.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	private DepartamentoRepository departamentoRepository;
	
	private FuncionarioService funcionarioService;
	
	@Autowired
	public DepartamentoService(DepartamentoRepository departamentoRepository,FuncionarioService funcionarioService ) {		
		this.departamentoRepository = departamentoRepository;
		this.funcionarioService = funcionarioService;
	}

	public void salvar(Departamento departamento) {
		departamentoRepository.save(departamento);
	}

	public Optional<Departamento> buscaPorId(Long idDepartamento) {
		return departamentoRepository.findById(idDepartamento);
	}

	public List<Departamento> buscarTodos() {
		return departamentoRepository.findAll();
	}

	public Departamento buscaPorPrimeiroDepartamentoCadastrado() {		
		return departamentoRepository.findFirstBy();		
	}
	
	@Transactional(readOnly = false)
	public void criarDepartamentoAtualizaFuncionario(Funcionario funcionario, Departamento departamento) {
		departamentoRepository.save(departamento);
		funcionario.setDepartamento(departamento);
		
		funcionarioService.salvar(funcionario);
	}	
}
