package br.edu.utfpr.jarc.atividade01.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Calendar;

public class Cliente {
	/**
	 * @author Erik Valcezio data: 11/07/2021 - Aplicação orientada a comunicação
	 * Classe com as dependencias necessaria para instanciar "Cliente" Socket que faz requisição
	 * com a class Servidor, para atender atividade01 Java Aplicado A Redes De Computadores na UTFPR
	 * @parm host é endereço de ip para requisição no servidor
	 * @parm porta é o número da porta 
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
	            System.out.println("O Resultado da validação (true or false) : " + resultado);
	            System.out.println(resultado ? "Seu CPF é Válido" : "Por favor, verifique o seu CPF");
				
			}catch (Exception e) {
				System.out.println("Erro cliente");
			}
				
	}

}
