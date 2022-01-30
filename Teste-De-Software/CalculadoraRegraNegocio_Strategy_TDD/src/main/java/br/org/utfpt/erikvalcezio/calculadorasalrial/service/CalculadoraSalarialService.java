package br.org.utfpt.erikvalcezio.calculadorasalrial.service;

import java.math.BigDecimal;

import br.org.utfpt.erikvalcezio.calculadorasalrial.model.Funcionario;
import br.org.utfpt.erikvalcezio.calculadorasalrial.model.RegraDescontoPorCargo;

public class CalculadoraSalarialService {

	public void obterDesconto(Funcionario funcionario, RegraDescontoPorCargo desconto) {
		BigDecimal reajuste = desconto.percentualDeDesconto(funcionario.getSalarioBase());
		funcionario.ajustarSalarioComDesconto(reajuste);		
	}

}
