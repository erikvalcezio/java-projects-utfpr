package br.edu.utfpr.jarc.atividade03.utils;

public enum SocketEnum {

	HOST1("127.0.0.1", "54325");
	
	private String host;
	private String port;
	

	SocketEnum(String host,String port) {
		this.host = host;
		this.port = port;
		
	}

	public String getPort() {
		return port;
	}

	public String getHost() {
		return host;
	}
	
	public static SocketEnum getServer(String host) {
		if (host == null) {
			return null;
		}
		
		for (SocketEnum socket : SocketEnum.values()) {
			if(socket.getHost().equalsIgnoreCase(host)) {
				return socket;
			}
		}
		
		return null;
	}

}
