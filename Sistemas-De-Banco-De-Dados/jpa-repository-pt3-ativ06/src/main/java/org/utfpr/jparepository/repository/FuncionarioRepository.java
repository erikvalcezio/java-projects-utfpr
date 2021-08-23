package org.utfpr.jparepository.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utfpr.jparepository.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findByNomeFuncionario(String nomeFuncionario);	
	
	List<Funcionario> findByNomeFuncionarioAndQuantidadeDependente(String nome, Integer qtdeDependentes);
	
	@Query(value = " SELECT f FROM Funcionario f LEFT JOIN Departamento d " + "ON d.codigoDepartamento = f.departamento"
			+ " WHERE d.nomeDepartamento =:nome")
	List<Funcionario> findPorNomeDepartamento(String nome);	

	List<Funcionario> findTopByOrderBySalarioDesc();	
	
	List<Funcionario> findFirst3ByOrderBySalarioDesc();	
	
	@Query (" select c from Funcionario c  where quantidade_dependente = 0 order by nome_func asc") 
	List<Funcionario> findByNomeFuncionarioSemDependenteAsc();
	
	@Query (" select c from Funcionario c  where c.salario > :determinadoSalario ")
	List<Funcionario> findByPorNomeFuncionarioComMaiorSalarioQ(Double determinadoSalario);
	
	@Query (value = " select * from Funcionarios where salario > ?1 ",
		 	nativeQuery = true)
	List<Funcionario> findByPorNomeFuncionarioComMaiorSalarioQN(Double determinadoSalario);
	
	@Query (name = "Funcionario.byQuantidadeDependente")
	List<Funcionario> findByQuantidadeDependente(int quantidadeDependente);
		
	@Query (name = "Funcionario.byNomeFuncionarioContains")	
	List<Funcionario> findByNomeFuncionarioContains(String nome);
	
	//Atividade 06
	@Procedure(procedureName= "proc_aumenta_salario")
	String procedureAumentaSalario(int porcetagem);
	
	//2
	@Query("SELECT f FROM Funcionario f LEFT JOIN Departamento d "
	+ "ON d.codigoDepartamento = f.departamento "
 	+ "WHERE d.nomeDepartamento =:nome_dpto and f.quantidadeDependente = :qtd_dependentes")
	List<Funcionario> findByDepartamentoAndQuantidadeDependente(@Param("nome_dpto") String nome_dpto, 
			@Param("qtd_dependentes") int qtd_dependentes);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Funcionarios f SET f.cod_dpto = (SELECT DISTINCT  d.cod_dpto from Funcionarios f"
			+ "			  left join Departamentos d"
			+ "			  ON d.cod_dpto = f.cod_dpto "
			+ "		      where d.nome_dpto = ?1 ) WHERE f.cod_dpto = (SELECT DISTINCT  d.cod_dpto from Funcionarios f "
			+ "			  left join Departamentos d"
			+ "			  ON d.cod_dpto = f.cod_dpto"
			+ "		      where d.nome_dpto = ?2 )", nativeQuery = true)
	int updateFuncionarioSetDepartamento(String departamento ,String departamentoTroca);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE from Funcionarios WHERE cod_dpto = (SELECT DISTINCT  d.cod_dpto from Funcionarios f "
			+ "			  left join Departamentos d ON d.cod_dpto = f.cod_dpto"
			+ "		      where d.nome_dpto = ?1 )", nativeQuery = true)
	   int deleteFuncionarioDepartamento(String departamento);

}
