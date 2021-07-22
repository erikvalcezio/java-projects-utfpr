package br.edu.utfpr.jarc.atividade01.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

import br.edu.utfpr.jarc.atividade01.util.ValidadorCpf;

public class Servidor {
	/**
	 * @author Erik Valcezio data: 11/07/2021 - Aplica��o orientada a comunica��o
	 *         Classe com as dependencias necessaria para instanciar "Servidor"
	 *         ServerSocket e Socket para atender atividade01 Java Aplicado A Redes
	 *         De Computadores na UTFPR
	 * @parm porta � o n�mero da porta que fica sendo escutada
	 */

	private DataInputStream entrada;
	private DataOutputStream saida;

	public void iniciarServidor(int porta) {

		String nome = Thread.currentThread().getName();
		System.out.println("Executando " + nome + " " + Calendar.getInstance().getTime() + " :");

		try (ServerSocket server = new ServerSocket(porta); Socket socket = server.accept();) {

			entrada = new DataInputStream(socket.getInputStream());
			saida = new DataOutputStream(socket.getOutputStream());

			saida.writeBoolean(new ValidadorCpf(entrada.readUTF()).verificarCPF());

		} catch (Exception e) {

			System.out.println("Porta: " + porta + " est� em uso, n�o foi poss�vel iniciar o servidor");

		}
	}
}
