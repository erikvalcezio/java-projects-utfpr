package br.org.utfpr.erikvalcezio;

import br.org.utfpr.erikvalcezio.exception.UnsupportedRuleException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Triangulo {

	private int a, b, c;

	public String validarTriangulo() {

		if (a < 0 || b < 0 || c < 0) {
			throw new UnsupportedRuleException("INV�LIDO: CONTEM N�MERO(S) NEGATIVO(S)!");
		}

		if (a == 0 || b == 0 || c == 0) {
			throw new UnsupportedRuleException("INV�LIDO: CONTEM N�MEROS IGUAIS A ZERO!");
		}

		if (!(a + b > c && b + c > a && a + c > b)) {
			throw new UnsupportedRuleException("INV�LIDO: A SOMA DE DOIS LADOS TEM QUE SER MAIOR QUE O TERCEIRO LADO!");
		}

		if (a == b && a == c) {
			return "EQUILATERO";
		} else if (a == b || a == c || b == c) {
			return "ISOSCELES";
		} else {
			return "ESCALENO";
		}
	}
}
