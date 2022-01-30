package exercicio2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(value = JUnit4.class)
public class IdentificadorTest {
	
	/**
	 * Classe de testes para equival�ncia exercio2 para Identificador.	 
	 */
	
	private Identificador identificador;
	

	/**
	 * Tamanho t do identificador, primeiro caracter c � uma letra e s� cont�m caracteres v�lidos.
	 * Cen�rio: (a1,V�lido), (1,3,5)	
	 */
	@Test
	public void testarIdentificadorComValorValido() {
		this.identificador = new Identificador("a1");		
			boolean isValid = this.identificador.validaEntradaDeDados(this.identificador.getEntradaDeDados());
			assertTrue(isValid);		
	} 
	
	/**
	 * Primeiro caracter c � uma letra.
	 * Cen�rio: (2B3, Inv�lido), (4)
	 * @throws UnsupportedOperationException is invalid
	 */
	@Test
	public void testarIdentificadorComValorDoPrimeiroCaracterInvalido(){
		this.identificador = new Identificador("2B3");
		try{
			this.identificador.validaEntradaDeDados(this.identificador.getEntradaDeDados());
		}catch (UnsupportedOperationException e) {
			final String mensageError = "Identificador inv�lido, o primeiro caracter deve ser uma letra!";
			assertEquals(mensageError, e.getMessage());
		}
	} 
	
	/**
	 * S� cont�m caracteres v�lidos.
	 * Cen�rio: (Z-12, Inv�lido), (6)	
	 */
	@Test
	public void testarIdentificadorComValorDeCaracteresInvalido(){
		this.identificador = new Identificador("Z-12");
		try{
			this.identificador.validaEntradaDeDados(this.identificador.getEntradaDeDados());
		}catch (UnsupportedOperationException e) {
			final String mensageError = "Identificador inv�lido, deve contem apenas letras e n�meros!";
			assertEquals(mensageError, e.getMessage());
		}
	}	
	
	/**
	 * (A1b2C3d, Inv�lido).
	 * Cen�rio: (A1b2C3d, Inv�lido), (2)	
	 */
	@Test
	public void testarIdentificadorQuantidadeDeCaracteresInvalido(){
		this.identificador = new Identificador("A1b2C3d");
		try{
			this.identificador.validaEntradaDeDados(this.identificador.getEntradaDeDados());
		}catch (UnsupportedOperationException e) {
			final String mensageError = "Tamanho inv�lido, deve ser de 1 a 6 caracter(s)!";
			assertEquals(mensageError, e.getMessage());
		}
	}
}


