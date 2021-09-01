package vatacaormi.driver;

import vatacaormi.servidor.Servidor;
import vatacaormi.cliente.Cliente;

public class Driver {
	/**
	 * @author Erik Eduardo Valcezio RA: 02329611 Faculdade: Universidade
	 *         Tecnológica Federal do Paraná Curso: Especialização em Tecnologia
	 *         Java/Java Aplicado A Redes De Computadores
	 * 
	 * @version 1.0.0, 29/08/2021
	 * @throws InterruptedException 
	 */

	/*
	 * O servidor RMI deverá estar apto a realizar duas funções: Contar todos os
	 * votos; Receber votos (contendo os nomes dos candidatos e os números de votos)
	 * Cada urna (cliente RMI) deverá enviar os nomes e o número de votos de cada
	 * candidato para o servidor; O servidor deverá exibir a apuração de votos
	 * atualizada a cada 5 segundos;
	 */
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> Servidor.iniciarServidorRmi(), "SERVIDOR VOTACAO").start();
		
		new Thread(() -> Cliente.iniciarClienteRmiUrna(), "URNA").start();
		
	}
}
