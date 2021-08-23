package br.org.utfpr.part2jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.utfpr.part2jpa.entity.Departamento;

@Repository
public interface DepartamentoRepositoryCrud extends JpaRepository <Departamento, Long> {
	
	List<Departamento> findByNomeDepartamento(String nome);
	
	//Spring Tools Suite
}
