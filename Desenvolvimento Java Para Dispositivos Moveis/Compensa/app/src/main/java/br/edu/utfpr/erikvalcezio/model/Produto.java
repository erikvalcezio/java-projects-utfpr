package br.edu.utfpr.erikvalcezio.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Data
@Builder
@Setter
public class Produto {

    private long id;
    private String descricao;
    private BigDecimal preco;
    private int quantidade;
    private String nomeUnidade;
    private String Setor; //trocar por Setor, futura implementação
}