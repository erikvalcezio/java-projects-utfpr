package org.utfpr.jparepository.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "funcionarios")
@NamedQuery(name = "Funcionario.byQuantidadeDependente", query = " from Funcionario c where c.quantidadeDependente = ?1 ")
@NamedNativeQuery(name = "Funcionario.byNomeFuncionarioContains", query = " select * from funcionarios where nome_func like CONCAT('%',?1,'%') ", resultClass = Funcionario.class)
public class Funcionario extends AbstractPersistable<Long> {
	/**
	 * Model Funcionario
	 */
	
	@Column(name = "nome_func", length = 64, nullable = false)	
	private @Getter @Setter String nomeFuncionario;
	
	@Column(name = "cpf", length = 64, nullable = false)	
	private @Getter @Setter String cpf;
	
	@Column(name = "quantidade_dependente", length = 64, nullable = false)
	private @Getter @Setter Integer quantidadeDependente;
	
	@Column(name = "salario", length = 64, nullable = false)	
	private @Getter @Setter Double salario;
	
	@Column(name = "cargo", length = 64, nullable = false)
	private @Getter @Setter String cargo;
	
	@Column(name = "data_contratacao", length = 64, nullable = false)
	private @Getter @Setter LocalDate dataContratacao;
		
	@ManyToOne
	@Nullable
	@JoinColumn(name = "cod_dpto")	
	private @Getter @Setter Departamento departamento;
	
	public Funcionario(){		
	}
	
	public Funcionario(String nomeFuncionario, String cpf, int quantidadeDependente, double salario, String cargo, LocalDate dataContratacao,
			Departamento departamento) {
		this.nomeFuncionario = nomeFuncionario;
		this.cpf = cpf;
		this.quantidadeDependente = quantidadeDependente;
		this.salario = salario;
		this.cargo = cargo;
		this.dataContratacao = dataContratacao;
		this.departamento = departamento;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	public String toString() {
		             return " \n\tCódigo do funcionário= \t\t" + super.getId() + 
							 "\n\tNome Funcionario = \t\t" + nomeFuncionario +	
							 "\n\tCPF = \t\t\t\t" + cpf + 
							 "\n\tQuantidade de dependentes = \t" + quantidadeDependente +
							 "\n\tSalario =\t\t\t" + salario + 
							 "\n\tCargo = \t\t\t" + cargo +
							 "\n\tData da Contração = \t\t" + dataContratacao +
							 "\n\tDepartamento = \t\t\t"+ departamento + "\n";
	}
	
}
