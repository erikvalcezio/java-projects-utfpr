package br.edu.utfpr.valcezio.tarefa.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

/**
 * Classe utilizada para representar um Jogador.
 *  
 * @author erik_
 */

@Data
@Builder
public class Jogador implements Serializable, Comparable<Jogador> {

	private static final long serialVersionUID = 1962830956119606997L;
	
	private String nome;
	private int posicao;
	private int pontuacao;

	@Override
	public int compareTo(Jogador p) {
		if (p.getPontuacao() > this.pontuacao) {
			return 1;
		} else if (p.getPontuacao() < this.pontuacao) {
			return -1;
		} else {
			return 0;
		}
	}

}
