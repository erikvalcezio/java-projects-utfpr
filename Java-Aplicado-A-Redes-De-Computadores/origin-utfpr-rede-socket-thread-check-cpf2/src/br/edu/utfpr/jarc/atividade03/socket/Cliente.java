package br.edu.utfpr.jarc.atividade03.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.edu.utfpr.jarc.atividade03.model.Pessoa;

public class Cliente {
	/**
	 * @author Erik Valcezio data: 11/07/2021 - Aplica��o orientada a comunica��o
	 *         Classe com as dependencias necessaria para instanciar "Cliente"
	 *         Socket que faz requisi��o com a class Servidor, para atender
	 *         atividade01 Java Aplicado A Redes De Computadores na UTFPR
	 * @parm host � endere�o de ip para requisi��o no servidor
	 * @parm porta � o n�mero da porta
	 * 
	 * @version 2.0.0
	 * 
	 * classe recebe dados via tela de nome e cpf para e envia por Objeto Pessoa para
	 * o servidor
	 */

	private ObjectInputStream entrada;
	private Pessoa pessoa;

	public Pessoa comunicarServidor(String host, int porta, String nome, String cpf) {

		try (Socket socket = new Socket(host, porta)) {
		
			 //Envia objeto Pessoa do cliente para o servidor 		 
			new ObjectOutputStream(socket.getOutputStream()).writeObject(new Pessoa(nome, cpf));			
			
			//Recebe o retorno do servidor	
			this.entrada = new ObjectInputStream(socket.getInputStream());
						
			return (Pessoa) this.entrada.readObject();

		} catch (Exception e) {
			//montar classe LOGGER da aplica��o, remover Sysout e subir a exception para tela do usu�rio "throw"
			System.out.println("Erro no envio de dados para o Servidor");
		}
		return pessoa;
	}
}
