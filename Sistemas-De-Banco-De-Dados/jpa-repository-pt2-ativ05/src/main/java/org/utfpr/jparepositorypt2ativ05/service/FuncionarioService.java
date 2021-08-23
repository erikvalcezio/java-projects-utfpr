package org.utfpr.jparepositorypt2ativ05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utfpr.jparepositorypt2ativ05.entity.Funcionario;
import org.utfpr.jparepositorypt2ativ05.repository.FuncionarioRepository;

@Service
public class FuncionarioService {	
	
	private final FuncionarioRepository funcinarioRepository;
	
	@Autowired
	public FuncionarioService(FuncionarioRepository funcinarioRepository) {		
		this.funcinarioRepository = funcinarioRepository;
	}
	
	public void salvar(Funcionario funcionario) {
		funcinarioRepository.save(funcionario);
	}
	
	public List<Funcionario> buscaTodosFuncionarios() {		
		return funcinarioRepository.findAll();
	}
	
	public List<Funcionario> buscaPorNomeFuncQtdDependente(String nomeFuncionario, int quantDependentes) {
		return funcinarioRepository.findByNomeFuncionarioAndQuantidadeDependente(nomeFuncionario , quantDependentes);
	}	
		
	public List<Funcionario> buscaTodosFuncionariosPorDepartamento(String departamento) {
		return funcinarioRepository.findPorNomeDepartamento(departamento);
	}

	public List<Funcionario> buscaPorFuncionarioComMaiorSalario() {		
		return funcinarioRepository.findTopByOrderBySalarioDesc();
	}

	public List<Funcionario> buscaPorFuncionarioComTresMaioresSalario() {		
		return funcinarioRepository.findFirst3ByOrderBySalarioDesc();
	}

	public List<Funcionario> buscaPorFuncionarioSemDependente() {
		return funcinarioRepository.findByNomeFuncionarioSemDependenteAsc();
	}
	
	public List<Funcionario> buscaPorFuncionarioComMaiorSalarioQ(Double determinadoSalario) {		
		return funcinarioRepository.findByPorNomeFuncionarioComMaiorSalarioQ(determinadoSalario);
	}

	public List<Funcionario> buscaPorFuncionarioComMaiorSalarioQN(Double determinadoSalario) {
		return funcinarioRepository.findByPorNomeFuncionarioComMaiorSalarioQN(determinadoSalario);
	}

	public List<Funcionario> buscaPorFuncionarioComQuantidadeDependentes(int i) {		
		return funcinarioRepository.findByQuantidadeDependente(i);
	}

	public List<Funcionario> buscaPorNomeFuncionarioParcial(String nomeParcial) {
		return funcinarioRepository.findByNomeFuncionarioContains(nomeParcial);
	}

}
