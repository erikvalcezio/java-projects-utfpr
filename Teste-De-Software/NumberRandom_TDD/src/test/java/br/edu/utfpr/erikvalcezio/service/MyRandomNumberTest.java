package br.edu.utfpr.erikvalcezio.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class MyRandomNumberTest {
		
	private MyRandomNumber myRdNumb;
	
	@Before
	public void builder() {
		 myRdNumb = new MyRandomNumber();		 
	}
	
	/**
	 * TDD begin >= end ou (begin<0 || end<0)
	 * @throws IntervaloInvalidoException
	 */
	@Test
	public void numeroInicialMaiorQueFinalTest(){
		
		try {
			this.myRdNumb.nextRandomNumber(2, 1);
		}catch(IntervaloInvalidoException ie) {
			final String msgErro = "begin eh maior ou igual end";
			assertEquals(ie.getMessage(), msgErro);
		}		
	}
	
	/**
	 * TDD begin >= end 
	 * @throws IntervaloInvalidoException
	 */
	@Test
	public void numeroInicialIgualFinalTest() throws IntervaloInvalidoException {
		
		try {
			this.myRdNumb.nextRandomNumber(2, 2);
		}catch(IntervaloInvalidoException ie) {
			final String msgErro = "begin eh maior ou igual end";
			assertEquals(msgErro, ie.getMessage());
		}		
	}
	

	/**
	 * TDD begin<0 
	 * @throws IntervaloInvalidoException
	 */
	@Test
	public void numeroInicialMenorQueFinalTest() {
		
		try {
			this.myRdNumb.nextRandomNumber(-1, 2);
		}catch(IntervaloInvalidoException ie) {
			final String msgErro = "begin eh menor que zero";
			assertEquals(msgErro, ie.getMessage());
		}	
		
	}	
	
	/** 
	 *  Não foi  necessário o caso (end<0),nunca entra nessa excessão
	 **/
			
	@Test	
	public void numeroAletorioNoIntervaloNaoPodeSerIgualAoAnterior() throws IntervaloInvalidoException {

		assertNotEquals(this.myRdNumb.nextRandomNumber(1, 2), this.myRdNumb.nextRandomNumber(1, 2));

	}
}