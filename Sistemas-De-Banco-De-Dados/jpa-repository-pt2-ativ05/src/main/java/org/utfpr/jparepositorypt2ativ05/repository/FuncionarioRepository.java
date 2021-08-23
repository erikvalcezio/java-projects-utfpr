package org.utfpr.jparepositorypt2ativ05.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.utfpr.jparepositorypt2ativ05.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findByNomeFuncionario(String nomeFuncionario);	
	
	List<Funcionario> findByNomeFuncionarioAndQuantidadeDependente(String nome, Integer qtdeDependentes);
	
	@Query(value = " SELECT f FROM Funcionario f LEFT JOIN Departamento d " + "ON d.codigoDepartamento = f.departamento"
			+ " WHERE d.nomeDepartamento =:nome")
	List<Funcionario> findPorNomeDepartamento(String nome);	

	List<Funcionario> findTopByOrderBySalarioDesc();	
	
	List<Funcionario> findFirst3ByOrderBySalarioDesc();	
	
	@Query (" select c from Funcionario c  where quantidade_dependentes = 0 order by nome asc") 
	List<Funcionario> findByNomeFuncionarioSemDependenteAsc();
	
	@Query (" select c from Funcionario c  where c.salario > :determinadoSalario ")
	List<Funcionario> findByPorNomeFuncionarioComMaiorSalarioQ(Double determinadoSalario);
	
	@Query (value = " select * from Funcionarios where salario > ?1 ",
		 	nativeQuery = true)
	List<Funcionario> findByPorNomeFuncionarioComMaiorSalarioQN(Double determinadoSalario);
	
	@Query (name = "Funcionario.byQuantidadeDependente")
	List<Funcionario> findByQuantidadeDependente(int quantidadeDependente);
	
	//arrumar
	@Query (name = "Funcionario.byNomeFuncionarioContains")	
	List<Funcionario> findByNomeFuncionarioContains(String nome);

}
