package org.utfpr.jparepository.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utfpr.jparepository.entity.Departamento;

@Qualifier("departamento")
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
	
	List<Departamento> findByNomeDepartamento(String nome);

	/**
	 * 
	 * @return
	 */
	Departamento findFirstBy();

}
