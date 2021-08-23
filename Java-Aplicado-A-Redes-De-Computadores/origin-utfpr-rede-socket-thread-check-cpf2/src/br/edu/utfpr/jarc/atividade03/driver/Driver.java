package br.edu.utfpr.jarc.atividade03.driver;

import javax.swing.JFrame;

import br.edu.utfpr.jarc.atividade03.view.ClienteServidorFrame;

public class Driver {
	/**
	 * @author Erik Valcezio data: 11/07/2021 - Aplicação orientada a comunicação
	 *         Comunicação entre Cliente e Servidor da atividade01 Aplicação já foi
	 *         desenvolvida para trabalhar com multithead para esse exemplo.
	 *  @version 1.0.0
	 *  @date 29/07/2021 
	 *  
	 * 	@author Erik Valcezio data: 31/07/2021 - Aplicação orientada a comunicação Multipla Threads
	 *  
	 *  Inclusão SWING, pode ser instaciada várias conexões em simultaneas ao mesmo tempo.
	 *  Thread Servidor socket fica ativa em quem ligou a aplicação via tela do SWING. 
	 *  O retorno é enviada em uma Jtable via tela "ClienteServidorFrame"
	 *  
	 *  Usado alguns Design patterns como singtlon e outros. 
	 *   
	 *  @version 2.0.0
	 *	@date 29/07/2021
	 *    
	 *    
	 */	

	public static void main(String[] args) {
		
		ClienteServidorFrame clienteSerivdor = new ClienteServidorFrame();
		clienteSerivdor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
