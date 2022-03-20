/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.evalcezio.consumir.api.somar.dois.numeros.rs;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author Erik Eduardo Valcezio
 */
public class RestClient {

	private WebTarget webTarget; // objeto do jarkata
	private Client client;
	private static final String BASE_URI = "http://localhost:8080/somar-dois-numeros/resources";

	public RestClient() {
		client = ClientBuilder.newClient(); ;
		webTarget = client.target(BASE_URI).path("rest");
	}

	// obter o resultado da soma dos números
	public int getSoma(int a, int b) throws ClientErrorException {
		int resource = webTarget.path("somar-dois-numeros").path("/" + a + "/" + b).request(MediaType.TEXT_PLAIN)
				.get(Integer.class);
		return resource;
	}

	public void close() {
		client.close();
	}

}
