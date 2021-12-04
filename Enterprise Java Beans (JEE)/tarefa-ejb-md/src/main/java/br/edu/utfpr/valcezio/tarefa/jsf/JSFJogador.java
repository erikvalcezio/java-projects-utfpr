package br.edu.utfpr.valcezio.tarefa.jsf;

import br.edu.utfpr.valcezio.tarefa.ejb.EjbStatefulJogador;
import br.edu.utfpr.valcezio.tarefa.ejb.EjbStatelessJogo;
import br.edu.utfpr.valcezio.tarefa.model.Jogador;
import java.io.Serializable;

import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import lombok.Data;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * Utilizando o aplicativo anterior (Jogo de somar).
 * O sistema deve enviar uma lista de ranking para o log do servidor, toda vez que houver um novo vencedor. 
 * @author erik_
 */

@Named(value = "jSFJogador")
@SessionScoped
@Data

public class JSFJogador implements Serializable{
    //private static final Logger LOGGER = LoggerFactory.getLogger(JSFJogador.class);

    private static final long serialVersionUID = -2776208011836779933L;

    @EJB
    private EjbStatefulJogador ejbStatefulJogador;

    @EJB
    private EjbStatelessJogo ejbStatelessJogo;
    
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
   
    @Resource(lookup = "java/Fila")
    private Queue fila;
    
    private List <Jogador> novoVencedor = null;  
       
    private String nome, messagemGameResultado, messageRecord;
    private int valorA, valorB, resultado;
    
    /**
     * cadastrar um novo jogador, caso exista com mesmo nome ignora
     */
    public void cadastrarJogador() {
        this.gerarValoresNovosRandom();
        ejbStatefulJogador.cadastrarJogador(this.nome);    
    }
    
    public List<Jogador> getAll(){       
        return ejbStatefulJogador.getAll();
    }
    
    public void gerarValoresNovosRandom(){
        this.valorA = ejbStatelessJogo.gerarValores();
        this.valorB = ejbStatelessJogo.gerarValores();
    }
    
    /**
     * Acionado pela GUI com botão verificar
     */
    public void verificarNovoVencedor(){
       this.messageRecord = "";
        boolean novoRecorde = (ejbStatefulJogador.verificarNovoVencedor(nome, (ejbStatelessJogo.calcularResultadoPontuacao(valorA, valorB, resultado))));
         
        //primeira execução, sem jogadores na lista e imprime na GUI
        if (this.novoVencedor == null){ 
            this.novoVencedor = ejbStatefulJogador.getListaDeJogadores().stream().sorted().limit(1).collect(toList());  
            this.send();
            imprimirNovoRecordGUI(this.novoVencedor.get(0).getNome());
        }    
   
        //mensagem na tela GUI de Certo ou Errado
        this.imprimirVerificaoResultadoGUI();
   
         
        //verifica a posição anterior com a nova, caso seja diferente envia a mensagem para o servidor MD e imprime na GUI
        if (novoRecorde){
            this.novoVencedor = ejbStatefulJogador.getAll().stream().limit(1).collect(toList()); 
            this.send(); 
            imprimirNovoRecordGUI(this.novoVencedor.get(0).getNome());
        }        
        
        this.gerarValoresNovosRandom();      
    }  
    
    /**
     * Acionado para apresentar na GUI um novo jogador com recorde
     * @param nome 
     */ 
    public void imprimirNovoRecordGUI(String nome){
        this.messageRecord = String.format("Novo Record do jogador: %s", nome);
    } 
    
    /**
    * Acionado para apresentar na GUI a correção do resultado como Certo ou Errado
    */
    public void imprimirVerificaoResultadoGUI(){        
        this.messagemGameResultado = ((valorA + valorB) == resultado ? "Certa" : "Errada" );  
    }
    
    /**
    * envia MD para servidor, o método só acionado quando existe um novo vencedor no top1 do rank
    */
    public void send(){
        try{
            JMSContext context = connectionFactory.createContext();
            context.createProducer().send(fila, (Serializable) this.novoVencedor);
        }catch(Exception e){
            System.out.println("Erro");
            System.out.println(e.getMessage());
        }
    }  
}
