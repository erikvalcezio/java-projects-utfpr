package vatacaormi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ValidadoraVotacao extends Remote {
	/**
	 * usada como common entre cliente servidor para simular execercio proposto
	 */
	
	/**
	 * Mostra apura��o dos votos
	 * @throws RemoteException
	 */
	void apuracaoDeTodosVotos() throws RemoteException;
	
	/**
	 * Receber o par�metros e contabilizar no sistema de vota��o
	 * Obs: n�o foi implementado com stream devido ao custo que tem na aplica��o. 
	 * @param numeroCandidato
	 * @param nomeCandidato
	 * @return
	 * @throws RemoteException
	 */
	boolean receberVoto(int numeroCandidato, String nomeCandidato) throws RemoteException;
	
	/**
	 * Retorna o candidato pelo seu n�mero 
	 * @param numeroCandidato
	 * @return
	 * @throws RemoteException
	 */
	String consultarCandidatos(int numeroCandidato) throws RemoteException;
	
	/**
	 * Exibe todos candidos
	 * @throws RemoteException
	 */
	void consultarCandidatos() throws RemoteException;
	
	/**
	 * Cadastra o candidato para participa��o da vota��o
	 * @param nome
	 * @param numero
	 * @throws RemoteException
	 */
	void cadastrarCandidatos(String nome, int numero) throws RemoteException;

}
