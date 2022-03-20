/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.edu.utfpr.evalcezio.consumir.api.somar.dois.numeros;

import br.edu.utfpr.evalcezio.consumir.api.somar.dois.numeros.rs.RestClient;

/**
 *
 * @author Erik Eduardo Valcezio
 */
public class Principal {
    public static void main(String[] args) {
        System.out.println("Iniciando aplicação");
        RestClient rc = new RestClient();
        
        //Informandos números para soma
        System.out.println("Informando números 1 e 2 para somar:");
        int soma= rc.getSoma(1,2);
                    
        System.out.println("Obtendo resultado da soma:");       
        
        System.out.println("A soma dos números é igual = " + soma);
        
        rc.close();
        
        System.out.println("FIM");
    }
}