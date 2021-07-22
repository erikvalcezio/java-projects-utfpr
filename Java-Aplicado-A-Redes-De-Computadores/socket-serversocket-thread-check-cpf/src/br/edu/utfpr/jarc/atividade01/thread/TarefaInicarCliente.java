package br.edu.utfpr.jarc.atividade01.thread;

import br.edu.utfpr.jarc.atividade01.socket.Cliente;

public class TarefaInicarCliente implements Runnable {
	/**
	 * @author Erik Valcezio data: 11/07/2021
	 * Implenta a interface Runnable para controle da Classe Cliente usando Thread
	 */
	
	private Cliente cliente;
	private String host;
	private int porta;	
	
	public TarefaInicarCliente(String host, int porta) {
		super();
		this.host = host;
		this.porta = porta;
	}
	
	@Override
	public void run() {
		cliente = new Cliente();
		System.out.println("Iniciando o cliente");
		cliente.iniciarCliente(host, porta);		
	}
	
	

}
