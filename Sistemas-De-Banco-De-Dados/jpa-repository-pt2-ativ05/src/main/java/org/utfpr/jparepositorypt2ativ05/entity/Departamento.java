package org.utfpr.jparepositorypt2ativ05.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departamentos")
public class Departamento{
	/**
	 * Model Departamento
	 * 
	 */	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_dpto", length = 10, nullable = false)
	private @Getter @Setter Long codigoDepartamento;
	
	@Column(name = "nome_dpto", length = 64, nullable = false)
	private @Getter @Setter String nomeDepartamento;	
	
	@OneToMany(mappedBy = "departamento")
	private @Getter @Setter List<Funcionario> funcionario;	
	
	public Departamento() {		
	}
	
	public Departamento(String nomeDptos) {
		this.nomeDepartamento = nomeDptos;
	}
	

	@Override
	public String toString() {
		return "\n\tCódigo departamento= " + codigoDepartamento  + ", Nome do Departamento : " + nomeDepartamento +"\n";
	}

}
