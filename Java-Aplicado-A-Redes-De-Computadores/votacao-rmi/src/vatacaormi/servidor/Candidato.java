package vatacaormi.servidor;

import java.io.Serializable;

public class Candidato implements Serializable {
	
	/**
	 * Armazena apuração dos candidatos 
	 */
	private static final long serialVersionUID = 5029210186399830500L;
	
	private String nome;
	private int numero;
	private int numeroTotalDeVotos;

	Candidato() {
	}

	public Candidato(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
		this.numeroTotalDeVotos = 0;
	}
	
	public Candidato(String nome, int numero, int voto) {
		this.nome = nome;
		this.numero = numero;
		this.numeroTotalDeVotos = voto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNumeroTotalDeVotos() {
		return numeroTotalDeVotos;
	}

	public void setNumeroTotalDeVotos(int numeroTotalDeVotos) {
		this.numeroTotalDeVotos=numeroTotalDeVotos;
	}
	
	@Override
	public String toString() {
		return "Nome do Candidato: " + nome +", número do candidato: " + numero + "\n";
	}

}
