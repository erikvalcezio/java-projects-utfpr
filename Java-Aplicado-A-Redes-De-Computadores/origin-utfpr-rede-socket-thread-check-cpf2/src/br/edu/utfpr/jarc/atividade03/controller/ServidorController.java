package br.edu.utfpr.jarc.atividade03.controller;

import br.edu.utfpr.jarc.atividade03.socket.Servidor;
import br.edu.utfpr.jarc.atividade03.utils.SocketEnum;

public class ServidorController  {
	
	private Servidor servidor;
	
	public ServidorController() {	
		
		if (this.servidor == null) {
			this.servidor = new Servidor();
		}
	}	

	public Thread excutarThreadServidor() throws UnsupportedOperationException{
		
		return new Thread(() -> {
			try {
				servidor.iniciarServidor(Integer.parseInt(SocketEnum.HOST1.getPort()));
			} catch (Exception e) {
				throw new UnsupportedOperationException(e.getMessage());
			}
		}, "Thread_Servidor");		
	}

	public String getStatusServidor() {
		return this.servidor.getStep();
	}
	
	public void turnOnServer(boolean onOff) {
		this.servidor.setTurnOn(onOff);
	}
	
	public boolean getturnOn() {
		return this.servidor.getTurnOn();		
	}	
	
}
