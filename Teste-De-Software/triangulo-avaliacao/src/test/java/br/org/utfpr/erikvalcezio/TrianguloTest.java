package br.org.utfpr.erikvalcezio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.org.utfpr.erikvalcezio.Triangulo;
import br.org.utfpr.erikvalcezio.exception.UnsupportedRuleException;

class TrianguloTest {

	private Triangulo triangulo;

	@Test
	@DisplayName(value = "Triângulo escaleno válido")
	void trianguloEscalenoValidoTest() {		
		assertEquals("ESCALENO", this.construirTrianguloTest(5, 7, 8));
	}

	@Test
	@DisplayName(value = "Triângulo equilatero válido")
	void trianguloEquilateroValidoTest() {		
		assertEquals("EQUILATERO", this.construirTrianguloTest(10, 10, 10));
	}

	@Test
	@DisplayName(value = "Triângulo isósceles válido - 3CTS PERMUTAÇÃO")
	void trianguloEhIsoscelesValidoPermutacaoMesmosValores() {		
		assertEquals("ISOSCELES", this.construirTrianguloTest(5, 10, 10));		
		assertEquals("ISOSCELES", this.construirTrianguloTest(10, 5, 10));
		assertEquals("ISOSCELES", this.construirTrianguloTest(10, 10, 5));
	}

	@Test
	@DisplayName(value = "Um valor zero")
	void trianguloContemValorComZeroEhInvalido() {

		UnsupportedRuleException thrown = Assertions.assertThrows(UnsupportedRuleException.class, () -> {
			this.construirTrianguloTest(5, 0, 5);			
		});

		Assertions.assertEquals("INVÁLIDO: CONTEM NÚMEROS IGUAIS A ZERO!", thrown.getMessage());
	}

	@Test
	@DisplayName(value = "Um valor negativo")
	void trianguloContemValorNegativoEhInvalido() {

		UnsupportedRuleException thrown = Assertions.assertThrows(UnsupportedRuleException.class, () -> {
			this.construirTrianguloTest(-5, 5, 5);			
		});

		Assertions.assertEquals("INVÁLIDO: CONTEM NÚMERO(S) NEGATIVO(S)!", thrown.getMessage());
	}

	@Test
	@DisplayName(value = "Condição de existencia de um triangulo"
			+ " A soma de 2 lados é exatamente igual ao terceiro lado - 3CTS")
	void trianguloComCondicaoComASomaDoisLadosIgualTerceiraEhInvalido() {

		UnsupportedRuleException thrown = Assertions.assertThrows(UnsupportedRuleException.class, () -> {
			try {

				this.construirTrianguloTest(5, 5, 10);

			} catch (UnsupportedRuleException case2) {

				try {

					this.construirTrianguloTest(5, 10, 5);

				} catch (UnsupportedRuleException case3) {

					this.construirTrianguloTest(10, 5, 5);

				}

			}
		});

		Assertions.assertEquals("INVÁLIDO: A SOMA DE DOIS LADOS TEM QUE SER MAIOR QUE O TERCEIRO LADO!",
				thrown.getMessage());
	}

	@Test
	@DisplayName(value = "Condição de existencia de um triangulo"
			+ " CT em que a soma de 2 lados é menor que o terceiro lado - 3CTS")
	void trianguloComCondicaoComASomaDoisLadosMenorQueTerceiraEhInvalido() {

		UnsupportedRuleException thrown = Assertions.assertThrows(UnsupportedRuleException.class, () -> {
			try {
				
				this.construirTrianguloTest(4, 5, 10);
				
			} catch (UnsupportedRuleException case2) {
				
				try {
					
					this.construirTrianguloTest(10, 5, 4);
					
				}catch(UnsupportedRuleException case3) {
					
					this.construirTrianguloTest(5, 10, 4);
				}
			}
		});

		Assertions.assertEquals("INVÁLIDO: A SOMA DE DOIS LADOS TEM QUE SER MAIOR QUE O TERCEIRO LADO!",
				thrown.getMessage());
	}
	
	public String construirTrianguloTest(int a, int b, int c) {
		triangulo = new Triangulo(a, b, c);
		return triangulo.validarTriangulo();
	}

}
