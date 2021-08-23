package br.org.utfpr.part2jpa.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends AbstractPersistable<Long> {
	/**
	 * Model Funcionario
	 */
	
	@Column(name = "nome", length = 64, nullable = false)	
	private @Getter @Setter String nomeFuncionario;
	
	@Column(name = "cpf", length = 64, nullable = false)	
	private @Getter @Setter String cpf;
	
	@Column(name = "quantidade_dependentes", length = 64, nullable = false)
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
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	public String toString() {
		return "Funcionario [ Código do funcionário= " + super.getId() + 
							 "\nNome Funcionario = " + nomeFuncionario +	
							 "\nCPF=\t\t " + cpf + 
							 "\nQuantidade de dependentes =" + quantidadeDependente +
							 "\nSalario =\t" + salario + 
							 "\nCargo =\t" + cargo +
							 "\nData da Contração =" + dataContratacao +
							 "\nDepartamento =" + departamento.getNomeDepartamento();
	}
	
}
