/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.valcezio.tarefa.ejb;

import br.edu.utfpr.valcezio.tarefa.model.Jogador;
import java.util.List;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * MD Consumidor acionado pelo Produtor JSFJogador
 * 
 * @author erik_
 */

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java/Fila"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }) // Recurso de
																											// consumo
																											// "destination"

public class EjbConsumidorRank implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("Mensagem Record Recebida");

        try {

            ((List<Jogador>) ((ObjectMessage) message).getObject()).forEach(System.out::print);

        } catch (JMSException e) {
                System.out.println(e.getCause());
        }

    }
}
