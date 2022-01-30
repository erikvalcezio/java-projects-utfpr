package br.org.utfpt.erikvalcezio.calculadorasalrial.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.org.utfpt.erikvalcezio.calculadorasalrial.model.Funcionario;
import br.org.utfpt.erikvalcezio.calculadorasalrial.model.RegraDescontoPorCargo;

@RunWith (value = JUnit4.class)
public class CalculadoraSalarialServiceTest {
	/**
	 * TDD	 
	 */
	
	/**
	 * Canario 1
	 * Caso o cargo seja DESENVOLVEDOR, 
	 * o funcionário terá desconto de 20% caso o salário seja maior ou igual que 3.000,00
	 */
	@Test
	public void funcionarioTeraDescontoVintePorcentoCasoSalarioMaiorOuIgualTresMil() {
		CalculadoraSalarialService service = new CalculadoraSalarialService();
		Funcionario funcionario = new Funcionario("Joao","joao@utfpr.org.br",new BigDecimal(3000.00),"DESENVOLVEDOR");
		
		service.obterDesconto(funcionario, RegraDescontoPorCargo.DESENVOLVEDOR);		
		
		assertEquals(new BigDecimal("2400.00"), funcionario.getSalarioBase());
	}
	
	/**
	 * Canario 2
	 * Caso o cargo seja DESENVOLVEDOR,	  
	 * ou apenas 10% caso o salário seja menor que isso.
	 */
	
	@Test
	public void funcionarioTeraDescontoDezPorcentoCasoSalarioMaiorOuIgualTresMil() {
		CalculadoraSalarialService service = new CalculadoraSalarialService();
		Funcionario funcionario = new Funcionario("Joao","joao@utfpr.org.br",new BigDecimal(2500.00),"DESENVOLVEDOR");
		
		service.obterDesconto(funcionario, RegraDescontoPorCargo.DESENVOLVEDOR);		
		
		assertEquals(new BigDecimal("2250.00"), funcionario.getSalarioBase());
	}
	
	/**
	 * Canario 3
	 * Caso o cargo seja DBA ou TESTADOR, 
	 * o funcionário terá desconto de 25% caso o salário seja maior ou igual que 2.000,00,
	 */
	@Test
	public void funcionarioTeraDescontoVinteCincoPorcentoCasoSalarioMaiorOuIgualDoisMil() {
		CalculadoraSalarialService service = new CalculadoraSalarialService();
		Funcionario funcionario = new Funcionario("Maria","maria@utfpr.org.br",new BigDecimal(2000.00),"DBA");
		
		service.obterDesconto(funcionario, RegraDescontoPorCargo.DBA);		
		
		assertEquals(new BigDecimal("1500.00"), funcionario.getSalarioBase());
	}
	
	/**
	 * Canario 4
	 * Caso o cargo seja DBA ou TESTADOR, 
	 * ou apenas 15% caso o salário seja menor que isso.
	 */
	
	@Test
	public void funcionarioTeraDescontoQuinzePorcentoCasoSalarioMenorDoisMil() {
		CalculadoraSalarialService service = new CalculadoraSalarialService();
		Funcionario funcionario = new Funcionario("Joao","joao@utfpr.org.br",new BigDecimal(1500.00),"TESTADOR");
		
		service.obterDesconto(funcionario, RegraDescontoPorCargo.TESTADOR);		
		
		assertEquals(new BigDecimal("1275.00"), funcionario.getSalarioBase());
	}
	
	/**
	 * Canario 5
	 * Caso o cargo seja GERENTE, 
	 * o funcionário terá desconto de 30% caso o salário seja maior ou igual que 5.000,00,
	 */
	@Test
	public void funcionarioTeraDescontoTrintaPorcentoCasoSalarioMaiorOuIgualCincoMil() {
		CalculadoraSalarialService service = new CalculadoraSalarialService();
		Funcionario funcionario = new Funcionario("Maria","maria@utfpr.org.br",new BigDecimal(5000.00),"GERENTE");
		
		service.obterDesconto(funcionario, RegraDescontoPorCargo.GERENTE);		
		
		assertEquals(new BigDecimal("3500.00"), funcionario.getSalarioBase());
	}
	
	/**
	 * Canario 6
	 * Caso o cargo seja DBA ou GERENTE, 
	 * ou apenas 20% caso o salário seja menor que isso.
	 */
	
	@Test
	public void funcionarioTeraDescontoVintePorcentoCasoSalarioMenorCincoMil() {
		CalculadoraSalarialService service = new CalculadoraSalarialService();
		Funcionario funcionario = new Funcionario("Joao","joao@utfpr.org.br",new BigDecimal(4500.00),"GERENTE");
		
		service.obterDesconto(funcionario, RegraDescontoPorCargo.GERENTE);		
		
		assertEquals(new BigDecimal("3600.00"), funcionario.getSalarioBase());
	}

}
