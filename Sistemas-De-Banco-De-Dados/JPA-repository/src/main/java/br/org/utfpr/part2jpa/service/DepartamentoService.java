package br.org.utfpr.part2jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.utfpr.part2jpa.entity.Departamento;
import br.org.utfpr.part2jpa.repository.DepartamentoRepositoryCrud;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepositoryCrud departamentoRepositoryImp;

	public void salvar(Departamento departamento) {
		departamentoRepositoryImp.save(departamento);
	}

	public Optional<Departamento> buscaPorId(Long idDepartamento) {
		return departamentoRepositoryImp.findById(idDepartamento);
	}

	public List<Departamento> buscarTodos() {
		return departamentoRepositoryImp.findAll();
	}

	public List<Departamento> buscarPorNome(String nomeDepartamento) {
		return departamentoRepositoryImp.findByNomeDepartamento(nomeDepartamento);
	}
	
	
}
