package vatacaormi.driver;

import vatacaormi.servidor.Servidor;
import vatacaormi.cliente.Cliente;

public class Driver {
	/**
	 * @author Erik Eduardo Valcezio RA: 02329611 Faculdade: Universidade
	 *         Tecnol�gica Federal do Paran� Curso: Especializa��o em Tecnologia
	 *         Java/Java Aplicado A Redes De Computadores
	 * 
	 * @version 1.0.0, 29/08/2021
	 * @throws InterruptedException 
	 */

	/*
	 * O servidor RMI dever� estar apto a realizar duas fun��es: Contar todos os
	 * votos; Receber votos (contendo os nomes dos candidatos e os n�meros de votos)
	 * Cada urna (cliente RMI) dever� enviar os nomes e o n�mero de votos de cada
	 * candidato para o servidor; O servidor dever� exibir a apura��o de votos
	 * atualizada a cada 5 segundos;
	 */
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> Servidor.iniciarServidorRmi(), "SERVIDOR VOTACAO").start();
		
		new Thread(() -> Cliente.iniciarClienteRmiUrna(), "URNA").start();
		
	}
}
