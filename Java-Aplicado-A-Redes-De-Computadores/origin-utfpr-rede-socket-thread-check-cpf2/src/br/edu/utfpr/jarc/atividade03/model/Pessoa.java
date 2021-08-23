package br.edu.utfpr.jarc.atividade03.model;

import java.io.Serializable;

import br.edu.utfpr.jarc.atividade03.util.ValidadorCpf;

public class Pessoa implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 33L;
	private String nome;
	private String cpf;	
	
	public Pessoa() {		
	}
	
	public Pessoa(String nome, String cpf) {
		super();
		this.nome = nome;
		this.cpf = cpf;
	}	

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	

}
