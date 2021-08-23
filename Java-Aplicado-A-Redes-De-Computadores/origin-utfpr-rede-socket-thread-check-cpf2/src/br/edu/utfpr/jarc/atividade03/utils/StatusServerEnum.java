package br.edu.utfpr.jarc.atividade03.utils;

public enum StatusServerEnum {
	
	STATUS_WAIT("Aguardando resposta do cliente"),
	STATUS_RUNNABLE("Aplicação SERVIDOR ATIVO na Porta: " + SocketEnum.HOST1.getPort()),
	STATUS_SEND("Resposta enviada para o cliente"),
	STATUS_ON("Ligado"),
	STATUS_OFF("Desligado");
	
	private String status;

	StatusServerEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

}
