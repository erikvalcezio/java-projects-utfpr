package br.org.utfpr.part2jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.utfpr.part2jpa.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	/**
	 * Busca funcionario pelo nome
	 * @param nomeFuncionario
	 * @return
	 */
	List<Funcionario> findByNomeFuncionario(String nomeFuncionario);	
	
	
	/**
	 * Listar um funcionário pelo seu nome e quantidade de dependentes utilizando consulta por palavras-chaves.
	 * @param dependente
	 * @return
	 */
	List<Funcionario> findByQuantidadeDependente(Integer quantidadeDependente);

}
