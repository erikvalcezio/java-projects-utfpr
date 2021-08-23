package org.utfpr.jparepositorypt2ativ05.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utfpr.jparepositorypt2ativ05.entity.Departamento;

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
