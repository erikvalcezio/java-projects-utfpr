package br.org.utfpr.erikvalcezio;

import java.util.Scanner;

import br.org.utfpr.erikvalcezio.exception.UnsupportedRuleException;

/**
 * Avaliação - Teste de Software
 * 
 * O programa lê três valores inteiros que representam os lados de um triângulo.
 * O programa informa se os lados formam um triângulo isósceles, escaleno ou
 * equilatero. Equilatero : 3 lados iguais. Isósceles : 2 lados iguais. Escaleno
 * : todos lados possuem medidas diferentes.
 * 
 * Condição: a soma de dois lados tem que ser maior que o terceiro lado.
 * 
 * @version 0.0.1
 * @author Erik Eduardo Valcezio – UTFPR
 */

public class AvaliacaoTesteDeSoftware {

	public static void main(String[] args) {	
		int a, b, c;

		try (Scanner input = new Scanner(System.in)) { // <- Closeable			

			System.out.print("# AVALIAÇÃO - TESTE DE SOFTWARE #");

			System.out.print("\nO PROGRAMA LÊ TRÊS VALORES INTEIROS QUE REPRESENTAM OS LADOS DE UM "
					+ "TRIÂNGULO E DETERMINA QUAL O TIPO ISÓSCELES, ESCALENO OU EQUILATERO");

			System.out.print("\nINFORME O VALOR LADO A: ");
			a = (input.nextInt());

			System.out.print("\nINFORME O VALOR LADO B: ");
			b = (input.nextInt());

			System.out.print("\nINFORME O VALOR LADO C: ");
			c = (input.nextInt());
			
			Triangulo t = new Triangulo(a, b , c);
			
			System.out.println(String.format("\nTRIÂNGULO: %s", t.validarTriangulo()));

		} catch (UnsupportedRuleException ex) {
			System.out.println(ex);
		}
	}
}
