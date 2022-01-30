package exercicio2;

public class Identificador {

	/**
	 * O programa deve determinar se um identificador � v�lido ou n�o. 
	 * Um identificador v�lido deve come�ar com uma letra e conter apenas letras ou d�gitos.
	 * Al�m disso, deve ter no m�nimo um caractere e no m�ximo seis caracteres de comprimento.
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
	 * determinar se um identificador � v�lido ou n�o.
	 * @param entradaDeDados
	 * @return 
	 * @throws Exception is invalid.
	 */
	public boolean validaEntradaDeDados(String entradaDeDados){

		boolean isValidInputData = true;

		if (!verificarTamanhoDoIdentificador(entradaDeDados)) {
			throw new UnsupportedOperationException("Tamanho inv�lido, deve ser de 1 a 6 caracter(s)!");
		}

		if (!verificarPrimeiroCaracterLetra(entradaDeDados)) {
			throw new UnsupportedOperationException("Identificador inv�lido, o primeiro caracter deve ser uma letra!");
		}

		if (!verificarCaracterLetraOuNumero(entradaDeDados)) {
			throw new UnsupportedOperationException("Identificador inv�lido, deve contem apenas letras e n�meros!");
		}

		return isValidInputData;
	}
	
	/**
	 * M�nimo um caractere e no m�ximo seis caracteres de comprimento.
	 * Tamanho t do identificador.	 
	 * @return true or false
	 */
	public boolean verificarTamanhoDoIdentificador(String entradaDeDados) {
		return entradaDeDados.trim().length() >= 1 && 
			   entradaDeDados.trim().length() <= 6;		
	}
	
	/**
	 * Primeiro caracter c � uma letra.	
	 * @return
	 */
	public boolean verificarPrimeiroCaracterLetra(String entradaDeDados) {
		return Character.isAlphabetic(entradaDeDados.charAt(0));		
	}
	
	/**
	 * S� cont�m caracteres v�lidos	 
	 * @return
	 */
	public boolean verificarCaracterLetraOuNumero(String entradaDeDados) {
		return entradaDeDados.chars().allMatch(Character::isLetterOrDigit); 	
	}
	
}
