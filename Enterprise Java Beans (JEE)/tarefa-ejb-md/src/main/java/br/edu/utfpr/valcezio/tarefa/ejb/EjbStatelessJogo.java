package br.edu.utfpr.valcezio.tarefa.ejb;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.Stateless;

/**
 *
 * @author erik_
 */

@Stateless 
public class EjbStatelessJogo { 
        
     /**
     * Soma resultado do Jogo 
     * @param a
     * @param b
     * @param resultado
     * @return 1 para acerto e 0 para erro
     */
    public int calcularResultadoPontuacao(int a, int b, int resultado) {
        return ((a + b) == resultado ? 1 : 0);
    }

    /**
     * Gerar numeros randonicos para os valores Jogo
     * @return
     */
    public int gerarValores() {
        return ThreadLocalRandom.current().nextInt(1, 10);		
    } 

}
