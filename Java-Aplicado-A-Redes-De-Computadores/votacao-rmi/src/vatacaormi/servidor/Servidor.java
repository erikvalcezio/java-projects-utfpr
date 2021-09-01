package vatacaormi.servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vatacaormi.common.ValidadoraVotacao;

public class Servidor implements ValidadoraVotacao {
	
	public Servidor() throws RemoteException {
		super();		
	}

	private static final List<Candidato> apuracacao = new ArrayList<>();

	@Override
	public void apuracaoDeTodosVotos() throws RemoteException {
		apuracacao.forEach(candidato -> System.out.println(
				String.format(" Nome %s, Totais de votos %s", candidato.getNome(), candidato.getNumeroTotalDeVotos())));
	}

	@Override
	public boolean receberVoto(int numeroCandidato, String nomeCandidato) throws RemoteException {
		for (Candidato candidatos : apuracacao) {
			if (candidatos.getNumero() == numeroCandidato && candidatos.getNome().equals(nomeCandidato)) {
				candidatos.setNumeroTotalDeVotos(candidatos.getNumeroTotalDeVotos() + 1);
				return true;
			}
		}
		return false;
	}

	@Override
	public String consultarCandidatos(int numeroCandidato) throws RemoteException {
		for (Candidato candidatos : apuracacao) {
			if (candidatos.getNumero() == numeroCandidato) {
				return candidatos.getNome();
			}
		}
		return String.format("Não localizado candidato com o número: %s", numeroCandidato);
	}

	@Override
	public void consultarCandidatos() throws RemoteException {
		apuracacao.forEach(System.out::println);
	}

	@Override
	public void cadastrarCandidatos(String nome, int numero) throws RemoteException {
		apuracacao.add(new Candidato(nome, numero));

	}

	public static void iniciarServidorRmi() {
		
		try {
			// lancar ao servidor de registro
			Registry registro = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

			// criar skeleton
			Servidor servidor = new Servidor();
			ValidadoraVotacao skeleton = (ValidadoraVotacao) UnicastRemoteObject.exportObject(servidor, 0);

			// Inserir no servidor de registro uma referencia aos metodos
			registro.rebind("validadoraVotacao", skeleton);

			while (true) {
				Thread.sleep(5000);
				System.out.println("\nApuração atualziada em: " + Calendar.getInstance().getTime());
				servidor.apuracaoDeTodosVotos();
			}

		} catch (Exception e) {

			System.err.println("Excecao: " + e.toString());
			e.printStackTrace();

		}

	}

}
