package br.org.utfpt.erikvalcezio.calculadorasalrial.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Data;

@Data
public class Funcionario {
	
	private String nome;
	private String email;
	private BigDecimal salarioBase;
	private String cargo;

	public Funcionario(String nome, String email, BigDecimal salarioBase, String cargo) {
		this.nome = nome;
		this.email = email;
		this.salarioBase = salarioBase;
		this.cargo = cargo;
	}

	
	public void ajustarSalarioComDesconto(BigDecimal reajuste) {
		this.salarioBase = this.salarioBase.subtract(reajuste).setScale(2, RoundingMode.HALF_UP);		
	}

}
