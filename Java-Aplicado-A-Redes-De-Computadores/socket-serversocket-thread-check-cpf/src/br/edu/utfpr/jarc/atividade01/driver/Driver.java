package br.edu.utfpr.jarc.atividade01.driver;
import br.edu.utfpr.jarc.atividade01.socket.Cliente;
import br.edu.utfpr.jarc.atividade01.socket.Servidor;
public class Driver {	
	/**
	 * @author Erik Valcezio data: 11/07/2021 - Aplicação orientada a comunicação
	 * Comunicação entre Cliente e Servidor da atividade01
	 * Aplicação já foi desenvolvida para trabalhar com multithead para esse exemplo.
	 */
	
	private static int porta = 54320;
	private static String host = "127.0.0.1";	
	
	public static void main(String[] args) {
		
		new Thread(() -> new Cliente().iniciarCliente(host, porta), "Thread_Cliente").start();		
		new Thread(() -> new Servidor().iniciarServidor(porta), "Thread_Servidor").start();
		
	}

}
