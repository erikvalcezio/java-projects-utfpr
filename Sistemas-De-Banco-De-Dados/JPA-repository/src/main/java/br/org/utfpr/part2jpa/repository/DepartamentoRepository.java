package br.org.utfpr.part2jpa.repository;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.utfpr.part2jpa.entity.Departamento;

@Qualifier("departamento")
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
