package br.edu.utfpr.jarc.atividade03.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import br.edu.utfpr.jarc.atividade03.model.Pessoa;
import br.edu.utfpr.jarc.atividade03.utils.StatusServerEnum;

public class Servidor {
	/**
	 * @author Erik Valcezio data: 11/07/2021 - Aplicação orientada a comunicação
	 *         Classe com as dependencias necessaria para instanciar "Servidor"
	 *         ServerSocket e Socket para atender atividade01 Java Aplicado A Redes
	 *         De Computadores na UTFPR	 * 
	 *         
	 *         Classe é iniciada por thread atravez da ServidorController
	 *         Recebe dados do cliente e retorna objeto, nesse momento não
	 *         está sendo aplicada nenhuma regra de validação
	 */	
	
	private ObjectInputStream entrada;
	private ObjectOutputStream saida;
	private String step;
	private boolean turnOn = false;
	
	public void iniciarServidor(int porta) {

		do {
			getTurnOn();		
						
			try (ServerSocket server = new ServerSocket(porta)) {
				
				this.step = StatusServerEnum.STATUS_WAIT.getStatus();				
				Socket socket = server.accept();					
				
				entrada = new ObjectInputStream(socket.getInputStream());				
				
				Pessoa p = (Pessoa) entrada.readObject();
							
				saida = new ObjectOutputStream(socket.getOutputStream());								
				
				saida.writeObject(p);				
				
				this.step = StatusServerEnum.STATUS_SEND.getStatus();
				
			} catch (IOException | ClassNotFoundException e) {
				this.step = StatusServerEnum.STATUS_RUNNABLE.getStatus();				
				this.setTurnOn(false);
			}
			
		} while (turnOn);
	}

	public String getStep() {
		return this.step;
	}

	public void setTurnOn(boolean turnOn) {
		this.turnOn = turnOn;
	}

	public boolean getTurnOn() {
		return this.turnOn;
		
	}

}
