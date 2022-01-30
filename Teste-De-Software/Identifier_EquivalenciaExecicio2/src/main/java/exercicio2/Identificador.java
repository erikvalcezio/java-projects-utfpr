package exercicio2;

public class Identificador {

	/**
	 * O programa deve determinar se um identificador é válido ou não. 
	 * Um identificador válido deve começar com uma letra e conter apenas letras ou dígitos.
	 * Além disso, deve ter no mínimo um caractere e no máximo seis caracteres de comprimento.
	 */
	
	private String entradaDeDados;	

	public Identificador (String entradaDeDados) {
		this.entradaDeDados = entradaDeDados;
	}
	
	/**
	 * Getter.
	 * @return
	 */
	public String getEntradaDeDados() {
		return entradaDeDados;
	}
	
	/**
	 * determinar se um identificador é válido ou não.
	 * @param entradaDeDados
	 * @return 
	 * @throws Exception is invalid.
	 */
	public boolean validaEntradaDeDados(String entradaDeDados){

		boolean isValidInputData = true;

		if (!verificarTamanhoDoIdentificador(entradaDeDados)) {
			throw new UnsupportedOperationException("Tamanho inválido, deve ser de 1 a 6 caracter(s)!");
		}

		if (!verificarPrimeiroCaracterLetra(entradaDeDados)) {
			throw new UnsupportedOperationException("Identificador inválido, o primeiro caracter deve ser uma letra!");
		}

		if (!verificarCaracterLetraOuNumero(entradaDeDados)) {
			throw new UnsupportedOperationException("Identificador inválido, deve contem apenas letras e números!");
		}

		return isValidInputData;
	}
	
	/**
	 * Mínimo um caractere e no máximo seis caracteres de comprimento.
	 * Tamanho t do identificador.	 
	 * @return true or false
	 */
	public boolean verificarTamanhoDoIdentificador(String entradaDeDados) {
		return entradaDeDados.trim().length() >= 1 && 
			   entradaDeDados.trim().length() <= 6;		
	}
	
	/**
	 * Primeiro caracter c é uma letra.	
	 * @return
	 */
	public boolean verificarPrimeiroCaracterLetra(String entradaDeDados) {
		return Character.isAlphabetic(entradaDeDados.charAt(0));		
	}
	
	/**
	 * Só contém caracteres válidos	 
	 * @return
	 */
	public boolean verificarCaracterLetraOuNumero(String entradaDeDados) {
		return entradaDeDados.chars().allMatch(Character::isLetterOrDigit); 	
	}
	
}
