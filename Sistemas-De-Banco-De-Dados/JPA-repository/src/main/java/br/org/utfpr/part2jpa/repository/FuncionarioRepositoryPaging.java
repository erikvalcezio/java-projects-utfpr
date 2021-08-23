package br.org.utfpr.part2jpa.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.org.utfpr.part2jpa.entity.Funcionario;

@Qualifier("funcionario")
@Repository
public interface FuncionarioRepositoryPaging extends PagingAndSortingRepository<Funcionario, Long>{
	/**
	 * Essa interface é apenas para efeito de estudos para testar a lista.
	 * Para a atividade parte 2 usar opção 4 correspondente no menu da aplicação
	 * que está representada na interface FuncionarioRepository
	 */
	 
	 /**
	 *
	 * @param nomeFuncionario
	 * @return
	 */
	List<Funcionario> findByNomeFuncionario(String nomeFuncionario);
	
	@Query("SELECT f FROM Funcionario f WHERE f.nomeFuncionario = :nomeFuncionario "
			+ "AND f.salario >= :salario AND f.dataContratacao = :data")
	
	/**
	 * 
	 * @param nome
	 * @param salario
	 * @param data
	 * @return
	 */
	List<Funcionario> findNomeFuncionarioSalarioMaior(String nomeFuncionario, Double salario, LocalDate data);
	
	@Query(value = "SELECT f FROM funcionarios f WHERE f.data_contratacao >= :data",
			nativeQuery = true)
	
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query(value = "SELECT f FROM Funcionarios f LEFT JOIN departamentos d "
					+ "ON d.cod_dpto = f.cod_dpto"
				 	+ "Departamentos d WHERE d.nome_dpto =:nome_departamento",
				 	nativeQuery = true)
	List<Funcionario> findPorNomeDepartamento(String nome_departamento);

}
