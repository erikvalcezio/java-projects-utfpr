package br.edu.utfpr.jarc.atividade03.driver;

import javax.swing.JFrame;

import br.edu.utfpr.jarc.atividade03.view.ClienteServidorFrame;

public class Driver {
	/**
	 * @author Erik Valcezio data: 11/07/2021 - Aplica��o orientada a comunica��o
	 *         Comunica��o entre Cliente e Servidor da atividade01 Aplica��o j� foi
	 *         desenvolvida para trabalhar com multithead para esse exemplo.
	 *  @version 1.0.0
	 *  @date 29/07/2021 
	 *  
	 * 	@author Erik Valcezio data: 31/07/2021 - Aplica��o orientada a comunica��o Multipla Threads
	 *  
	 *  Inclus�o SWING, pode ser instaciada v�rias conex�es em simultaneas ao mesmo tempo.
	 *  Thread Servidor socket fica ativa em quem ligou a aplica��o via tela do SWING. 
	 *  O retorno � enviada em uma Jtable via tela "ClienteServidorFrame"
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
