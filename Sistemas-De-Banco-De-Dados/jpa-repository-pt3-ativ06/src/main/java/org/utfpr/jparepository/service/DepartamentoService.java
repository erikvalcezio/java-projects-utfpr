package org.utfpr.jparepository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utfpr.jparepository.entity.Departamento;
import org.utfpr.jparepository.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	public DepartamentoService(DepartamentoRepository departamentoRepository) {		
		this.departamentoRepository = departamentoRepository;
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
}
