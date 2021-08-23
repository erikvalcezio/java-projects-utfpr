package br.edu.utfpr.jarc.atividade01.util;

public class ValidadorCpf {
	/**
	 * @author Erik Valcezio
	 * data: 11/07/2021
	 */
	
	private String cpf;

	public ValidadorCpf(String cpf) {
		this.cpf = cpf.trim();
	}
	/**	 
	 * @return faz todas validações para documento CPF
	 */
	public boolean verificarCPF() {
		System.out.println("Fazendo a validação do CPF");
		removerCaracteres();
		return (verificarSeTamanhoInvalido() && verificarSeDigIguais() && calculoComCpf());
	}
	
	/**
	 * Limpa caracteres especiais, geralmente digitados no CPF
	 */

	private void removerCaracteres() {
		this.cpf = this.cpf.replace("-", "");
		this.cpf = this.cpf.replace(".", "");		
	}

	public boolean verificarSeTamanhoInvalido() {

		if (this.cpf.length() == 11)
			return true;
		return false;
	}
	
	/**	 
	 * @return valida o sequencial se está igual 0
	 * http://www.receita.fazenda.gov.br/aplicacoes/atcta/cpf/consultapublica.asp
	 */

	private boolean verificarSeDigIguais() {	
		char primDig = '0';
		char[] charCpf = this.cpf.toCharArray();

		for (char c : charCpf)

			if (c != primDig)
				return true;
		return false;

	}
	
	/**
	 * @return valida primeiro e segundo digito do CPF para verificar integridade "true = OK" 
	 */
	private boolean calculoComCpf() {
		return validaPrimeiroDigCpf() && validaSegundoDigCpf();
	}
	
	/**
	 * @return valida o primeiro digito do CPF na base do calculo com resto para comparar
	 */	
	private boolean validaPrimeiroDigCpf() {
		Integer total = 0;
		String validaDig = cpf.substring(0, 9);
		char[] charCpf = validaDig.toCharArray();
		for (int i = 0, x = 10; x >= 2; x--, i++) {
			total += ((Integer.parseInt(String.valueOf(charCpf[i]))) * x);
		}
		total = ((total * 10) % 11);
		return (total.equals(Integer.parseInt(cpf.substring(9, 10)))); 
	}
	
	/**
	 * @return valida o segundo digito do CPF na base do calculo com resto para comparar
	 */	
	private boolean validaSegundoDigCpf() {
		Integer total = 0;
		String validaDig = cpf.substring(0, 10);
		char[] charCpf = validaDig.toCharArray();
		for (int i = 0, x = 11; x >= 2; x--, i++) {
			total += ((Integer.parseInt(String.valueOf(charCpf[i]))) * x);
		}
		total = ((total * 10) % 11);
		return (total.equals(Integer.parseInt(cpf.substring(10, 11)))); // compara sobra com o digito
	}
}

