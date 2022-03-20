package br.org.utfpr.erikvalcezio;

import java.util.Scanner;

import br.org.utfpr.erikvalcezio.exception.UnsupportedRuleException;

/**
 * Avalia��o - Teste de Software
 * 
 * O programa l� tr�s valores inteiros que representam os lados de um tri�ngulo.
 * O programa informa se os lados formam um tri�ngulo is�sceles, escaleno ou
 * equilatero. Equilatero : 3 lados iguais. Is�sceles : 2 lados iguais. Escaleno
 * : todos lados possuem medidas diferentes.
 * 
 * Condi��o: a soma de dois lados tem que ser maior que o terceiro lado.
 * 
 * @version 0.0.1
 * @author Erik Eduardo Valcezio � UTFPR
 */

public class AvaliacaoTesteDeSoftware {

	public static void main(String[] args) {	
		int a, b, c;

		try (Scanner input = new Scanner(System.in)) { // <- Closeable			

			System.out.print("# AVALIA��O - TESTE DE SOFTWARE #");

			System.out.print("\nO PROGRAMA L� TR�S VALORES INTEIROS QUE REPRESENTAM OS LADOS DE UM "
					+ "TRI�NGULO E DETERMINA QUAL O TIPO IS�SCELES, ESCALENO OU EQUILATERO");

			System.out.print("\nINFORME O VALOR LADO A: ");
			a = (input.nextInt());

			System.out.print("\nINFORME O VALOR LADO B: ");
			b = (input.nextInt());

			System.out.print("\nINFORME O VALOR LADO C: ");
			c = (input.nextInt());
			
			Triangulo t = new Triangulo(a, b , c);
			
			System.out.println(String.format("\nTRI�NGULO: %s", t.validarTriangulo()));

		} catch (UnsupportedRuleException ex) {
			System.out.println(ex);
		}
	}
}
