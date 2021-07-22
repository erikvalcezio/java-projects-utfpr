package br.edu.utfpr.jarc.atividade01.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Calendar;

public class Cliente {
	/**
	 * @author Erik Valcezio data: 11/07/2021 - Aplica��o orientada a comunica��o
	 * Classe com as dependencias necessaria para instanciar "Cliente" Socket que faz requisi��o
	 * com a class Servidor, para atender atividade01 Java Aplicado A Redes De Computadores na UTFPR
	 * @parm host � endere�o de ip para requisi��o no servidor
	 * @parm porta � o n�mero da porta 
	 */
	
	public void iniciarCliente(String host, int porta) {
		String nome = Thread.currentThread().getName();
		System.out.println("Executando " + nome + " " + Calendar.getInstance().getTime() + " :");
		
			try (Socket socket = new Socket(host, porta)){
							
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Digite o CPF : ");
				String cpf = br.readLine();
						
				new DataOutputStream(socket.getOutputStream()).writeUTF(cpf);
				  
				boolean resultado= new DataInputStream(socket.getInputStream()).readBoolean();
	            System.out.println("O Resultado da valida��o (true or false) : " + resultado);
	            System.out.println(resultado ? "Seu CPF � V�lido" : "Por favor, verifique o seu CPF");
				
			}catch (Exception e) {
				System.out.println("Erro cliente");
			}
				
	}

}
