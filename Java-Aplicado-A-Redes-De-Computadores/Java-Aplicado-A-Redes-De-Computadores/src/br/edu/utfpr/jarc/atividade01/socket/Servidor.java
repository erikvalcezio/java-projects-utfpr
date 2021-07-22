package br.edu.utfpr.jarc.atividade01.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

import br.edu.utfpr.jarc.atividade01.util.ValidadorCpf;

public class Servidor {
	/**
	 * @author Erik Valcezio data: 11/07/2021 - Aplicação orientada a comunicação
	 *         Classe com as dependencias necessaria para instanciar "Servidor"
	 *         ServerSocket e Socket para atender atividade01 Java Aplicado A Redes
	 *         De Computadores na UTFPR
	 * @parm porta é o número da porta que fica sendo escutada
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

			System.out.println("Porta: " + porta + " está em uso, não foi possível iniciar o servidor");

		}
	}
}
