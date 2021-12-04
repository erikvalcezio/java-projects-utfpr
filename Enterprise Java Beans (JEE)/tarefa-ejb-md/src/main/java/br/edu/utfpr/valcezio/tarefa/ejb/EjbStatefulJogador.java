package br.edu.utfpr.valcezio.tarefa.ejb;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateful;
import br.edu.utfpr.valcezio.tarefa.model.Jogador;
import lombok.Getter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * Regras de Negócio
 *
 * @author erik_
 */
@Stateful
public class EjbStatefulJogador {
//private static final Logger LOGGER = LoggerFactory.getLogger(EjbStatefulJogador.class);

    @Getter
    private final List<Jogador> listaDeJogadores;

    public EjbStatefulJogador() {
        this.listaDeJogadores = new ArrayList<>();
    }

    public List<Jogador> cadastrarJogador(String nome) {

        boolean JogadorNaoExiste = this.listaDeJogadores.stream()
                        .anyMatch(jogadorExiste -> jogadorExiste.getNome().contains(nome.toUpperCase()));

        if (!JogadorNaoExiste) {
                this.listaDeJogadores.add(Jogador.builder().nome(nome.toUpperCase()).pontuacao(0).posicao(0).build());
        }

        return this.listaDeJogadores;
    }

    public List<Jogador> getAll() {
        return this.listaDeJogadores.stream().sorted().collect(Collectors.toList());
    }

    public void adicionarPontuacao(String nome, int ponto) {
        this.listaDeJogadores.stream().filter(jogadores -> jogadores.getNome().equals(nome.toUpperCase()))
                        .forEach(jogador -> jogador.setPontuacao(jogador.getPontuacao() + ponto));
    }

    public void atualizarListaPorPosicao() {
        this.getAll()
                        .forEach(jogador -> this.listaDeJogadores.set(this.listaDeJogadores.indexOf(jogador),
                                        Jogador.builder().nome(jogador.getNome()).pontuacao(jogador.getPontuacao())
                                                        .posicao(this.getAll().indexOf(jogador) + 1).build()));
    }

    /**
     * computa a pontuacao, faz reorg da lista para pontuacao e valida se existe novo vencedor.
     * se for mesmo jogador não existe novo vencedor return false.
     * caso exista um novo vencedor return true.
     * @param nome
     * @param pontucao
     * @return 
     */
    public boolean verificarNovoVencedor(String nome, int pontucao) {

        // Pega primeira posição lista antes de atualizar pontuação
        List<Jogador> jogadores = this.listaDeJogadores.stream().sorted().limit(1).collect(toList());
     
        // atualizar pontucao
        this.adicionarPontuacao(nome, pontucao);

        // atualizar a posição
        this.atualizarListaPorPosicao();

        // lista atualizada com primeira posição
        List<Jogador> topJogador = getAll().stream().limit(1).collect(toList());

        return !jogadores.get(0).getNome().equals(topJogador.get(0).getNome());
    }

}