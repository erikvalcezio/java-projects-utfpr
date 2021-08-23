package br.org.utfpr.raceconditionsections;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

public class MotorcycleRace {
	/**
	 * @author Erik Eduardo Valcezio RA: 02329611 03/08/2021
	 * @Faculdade Universidade Tecnológica Federal do Paraná
	 * @data 03/08/2021
	 * @curso Especialização em Tecnologia Java
	 * @máteria Linguagem De Programação Java II (2020)
	 * @desafio B3E2 - Corrida de motos
	 * 
	 */

	private Hashtable<String, Integer> hashMapCompetidor = new Hashtable<String, Integer>();
	private int CompetitorsOnTheTrack;
	private int position;
	private int contadorGrandGP;
	private int totalruns;
	private int totalCompetitor;

	public MotorcycleRace() {
		this.position = StartRace.UM;
		this.contadorGrandGP = 0;
	}

	/**
	 * registrar os competidor na competição, por nome (chave) e pontual inicial
	 * (valor) contabiliza o total de competidores na variável totalOfCompetitors
	 */
	public void registrationCompetitor(int totalruns, int totalCompetitor) {
		this.totalruns = totalruns;
		this.totalCompetitor = totalCompetitor;
		hashMapCompetidor.put(Thread.currentThread().getName(), 0);
		waitForTheBeginning();
	}

	/**
	 * monitora os competidores para aguardar o inico de cada nova prova/corrida;
	 * compara o número de competidores da prova (CompetitorsOnTheTrack) com número
	 * de total de competidores (this.totalCompetitor) para configurar uma corrida
	 * no método loadRace() esperando a notificação para sair do sincronismo e
	 * iniciar uma nova corrida com método runRace()
	 */
	public void waitForTheBeginning() {

		synchronized (this) {

			try {
				this.CompetitorsOnTheTrack++;
				if (this.CompetitorsOnTheTrack == this.totalCompetitor) {
					loadRace();
				} else {
					this.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		runRace();
	}

	/**
	 * monitora(sincronizado) e recicla variáveis de controle this.position e
	 * this.CompetitorsOnTheTrack faz o modo interativo em tela da corrida, coordena
	 * por uma thread liberando recurso das demais Threads que estão aguardando
	 * (this.wait()) por notificação(ALL).
	 */
	
	synchronized void loadRace() {

		this.position = StartRace.UM;
		System.out.println("\n######### CORRIDA DE NÚMERO " + ((this.contadorGrandGP++) + 1) + " #########");
		System.out.println("TODOS OS " + CompetitorsOnTheTrack + " COMPETIDORES NA PISTA");
		System.out.println("CONTAGEM REGRESSIVA:");

		for (int i = 5; i > 0; i--) {
			System.out.println(i);
			sleepThread(1000);
		}

		System.out.println("BANDEIRA VERDE - INICIO DA CORRIDA !");

		for (int i = 0; i < 100; i++) {
			System.out.print(":");
			sleepThread(50);
		}
		if (position == StartRace.UM)
			System.out.println("\nBANDEIRA QUADRICULADA - FINAL DA CORIIDA !\n");

		this.CompetitorsOnTheTrack = 0;
		this.notifyAll();
	}

	/**
	 * Classifica os competidores por ordem de chegada dentro do monitor e soma a pontuação da corrida
	 * faz a validação/controle do total da corrida para iniciar uma nova corrida em "waitForTheBeginning()"
	 * ou finalizar mostrando placar geral da competição.
	 */
	private void runRace() {
		sleepThread(100);

		synchronized (this) {

			int pontuacaoDarodada = hashMapCompetidor.size() - position + 1;
			System.out.println("=> " + Thread.currentThread().getName() + " cruzou a linha de chegada em "
					+ String.format("%02d", position) + "º lugar! " + "Ganhando " + pontuacaoDarodada + " pontos ");

			hashMapCompetidor.merge(Thread.currentThread().getName(), (pontuacaoDarodada), Integer::sum);
			this.position++;
		}

		if (this.contadorGrandGP < this.totalruns) {
			waitForTheBeginning();
		} else {			
			
			this.CompetitorsOnTheTrack++;
			if (this.CompetitorsOnTheTrack == this.totalCompetitor) {
				System.out.println("\n####### GrandGP -> Classificação geral dos competidores #######\n");

				hashMapCompetidor.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
						.forEach(System.out::println);
			}
		}
	}

	/**
	 * Util - adormece a Thread
	 * 
	 * @param tempo
	 */
	public void sleepThread(int tempo) {
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}

class StartRace {
	/**
	 * Classe principal para load da aplicação
	 */
	private static final MotorcycleRace RACE = new MotorcycleRace();
	final static int UM = 1;
	
	/**
	 * Inicia a execução via método instantiateCompetidorRace(int n) que é recursivo
	 * @param args
	 */
	public static void main(String[] args) {

		instantiateCompetidorRace(UM);
	}
	
	/**
	 * instanciar a quantidade de 10 threads à partir do valor "n". (método
	 * recursivo) cada thread tem a representação de um competidor na classe
	 * MotorcycleRace no método registrationCompetitor(número de corridas, total de
	 * competidores) <- para o teste foi deixado 10 corridas e 10 competidores (hardcode)
	 *  
	 * @param n
	 */
	public final static void instantiateCompetidorRace(int n) {
		if (n > 10)
			return;

		new Thread(() -> RACE.registrationCompetitor(10, 10), "Competidor #N".concat(String.format("%02d", n))).start();
		instantiateCompetidorRace(n + UM);
	}
}
