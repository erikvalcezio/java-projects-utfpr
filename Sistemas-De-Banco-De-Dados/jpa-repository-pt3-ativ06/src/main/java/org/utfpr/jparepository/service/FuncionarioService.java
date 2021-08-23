package org.utfpr.jparepository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utfpr.jparepository.entity.Funcionario;
import org.utfpr.jparepository.repository.FuncionarioRepository;

@Service
public class FuncionarioService {	
	
	private final FuncionarioRepository funcionarioRepository;
	
	@Autowired
	public FuncionarioService(FuncionarioRepository funcinarioRepository) {		
		this.funcionarioRepository = funcinarioRepository;
	}
	
	public void salvar(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}
	
	public List<Funcionario> buscaTodosFuncionarios() {		
		return funcionarioRepository.findAll();
	}
	
	public List<Funcionario> buscaPorNomeFuncQtdDependente(String nomeFuncionario, int quantDependentes) {
		return funcionarioRepository.findByNomeFuncionarioAndQuantidadeDependente(nomeFuncionario , quantDependentes);
	}	
		
	public List<Funcionario> buscaTodosFuncionariosPorDepartamento(String departamento) {
		return funcionarioRepository.findPorNomeDepartamento(departamento);
	}

	public List<Funcionario> buscaPorFuncionarioComMaiorSalario() {		
		return funcionarioRepository.findTopByOrderBySalarioDesc();
	}

	public List<Funcionario> buscaPorFuncionarioComTresMaioresSalario() {		
		return funcionarioRepository.findFirst3ByOrderBySalarioDesc();
	}

	public List<Funcionario> buscaPorFuncionarioSemDependente() {
		return funcionarioRepository.findByNomeFuncionarioSemDependenteAsc();
	}
	
	public List<Funcionario> buscaPorFuncionarioComMaiorSalarioQ(Double determinadoSalario) {		
		return funcionarioRepository.findByPorNomeFuncionarioComMaiorSalarioQ(determinadoSalario);
	}

	public List<Funcionario> buscaPorFuncionarioComMaiorSalarioQN(Double determinadoSalario) {
		return funcionarioRepository.findByPorNomeFuncionarioComMaiorSalarioQN(determinadoSalario);
	}

	public List<Funcionario> buscaPorFuncionarioComQuantidadeDependentes(int i) {		
		return funcionarioRepository.findByQuantidadeDependente(i);
	}

	public List<Funcionario> buscaPorNomeFuncionarioParcial(String nomeParcial) {
		return funcionarioRepository.findByNomeFuncionarioContains(nomeParcial);
	}

	public String aumentaSalarioTodosFuncionarios(int porcetagem) {
		return funcionarioRepository.procedureAumentaSalario(porcetagem);
	}

	public List<Funcionario> buscaPorDepartamentoAndQuantidadeDependente(String nomeDepartamento, int qtd_dependentes) {
		return funcionarioRepository.findByDepartamentoAndQuantidadeDependente(nomeDepartamento, qtd_dependentes);
	}

	public int atualizarFuncionarioDepartamento(String departamento, String departamentoTroca) {	
		return funcionarioRepository.updateFuncionarioSetDepartamento(departamento,departamentoTroca);
	}
	
	public int deletarFuncionarioDepartamento(String departamento) {
		return funcionarioRepository.deleteFuncionarioDepartamento(departamento);
	}
}
