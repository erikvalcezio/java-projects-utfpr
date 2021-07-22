package br.edu.utfpr.jarc.atividade01.thread;

import br.edu.utfpr.jarc.atividade01.socket.Servidor;

public class TarefaInicarServidor implements Runnable {
	/**
	 * @author Erik Valcezio data: 11/07/2021
	 * Implenta a interface Runnable para controle da Classe Servidor usando Thread
	 */

	private Servidor servidor;
	private int porta;
	
	public TarefaInicarServidor(int porta) {
		super();		
		this.porta = porta;
	}

	@Override
	public void run() {
		servidor = new Servidor();
		System.out.println("Iniciando o servidor");
		servidor.iniciarServidor(porta);
	}
}

