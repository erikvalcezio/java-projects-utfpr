package exercicio2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(value = JUnit4.class)
public class IdentificadorTest {
	
	/**
	 * Classe de testes para equivalência exercio2 para Identificador.	 
	 */
	
	private Identificador identificador;
	

	/**
	 * Tamanho t do identificador, primeiro caracter c é uma letra e só contém caracteres válidos.
	 * Cenário: (a1,Válido), (1,3,5)	
	 */
	@Test
	public void testarIdentificadorComValorValido() {
		this.identificador = new Identificador("a1");		
			boolean isValid = this.identificador.validaEntradaDeDados(this.identificador.getEntradaDeDados());
			assertTrue(isValid);		
	} 
	
	/**
	 * Primeiro caracter c é uma letra.
	 * Cenário: (2B3, Inválido), (4)
	 * @throws UnsupportedOperationException is invalid
	 */
	@Test
	public void testarIdentificadorComValorDoPrimeiroCaracterInvalido(){
		this.identificador = new Identificador("2B3");
		try{
			this.identificador.validaEntradaDeDados(this.identificador.getEntradaDeDados());
		}catch (UnsupportedOperationException e) {
			final String mensageError = "Identificador inválido, o primeiro caracter deve ser uma letra!";
			assertEquals(mensageError, e.getMessage());
		}
	} 
	
	/**
	 * Só contém caracteres válidos.
	 * Cenário: (Z-12, Inválido), (6)	
	 */
	@Test
	public void testarIdentificadorComValorDeCaracteresInvalido(){
		this.identificador = new Identificador("Z-12");
		try{
			this.identificador.validaEntradaDeDados(this.identificador.getEntradaDeDados());
		}catch (UnsupportedOperationException e) {
			final String mensageError = "Identificador inválido, deve contem apenas letras e números!";
			assertEquals(mensageError, e.getMessage());
		}
	}	
	
	/**
	 * (A1b2C3d, Inválido).
	 * Cenário: (A1b2C3d, Inválido), (2)	
	 */
	@Test
	public void testarIdentificadorQuantidadeDeCaracteresInvalido(){
		this.identificador = new Identificador("A1b2C3d");
		try{
			this.identificador.validaEntradaDeDados(this.identificador.getEntradaDeDados());
		}catch (UnsupportedOperationException e) {
			final String mensageError = "Tamanho inválido, deve ser de 1 a 6 caracter(s)!";
			assertEquals(mensageError, e.getMessage());
		}
	}
}


