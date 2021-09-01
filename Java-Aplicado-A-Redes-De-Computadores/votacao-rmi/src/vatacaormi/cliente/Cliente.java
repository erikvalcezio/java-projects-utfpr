package vatacaormi.cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import vatacaormi.common.ValidadoraVotacao;

public class Cliente {	
	
	public static void iniciarClienteRmiUrna() {

		try {
						
			//Permiss�o para acesso externo
			//System.setProperty ("java.security.policy","file:rmiprj.policy");
						
			//acessa o servi�o de registro
			Registry registro = LocateRegistry.getRegistry("127.0.0.1", Registry.REGISTRY_PORT);
			
			//procurar a referencia
			ValidadoraVotacao stub = (ValidadoraVotacao) registro.lookup("validadoraVotacao");
			
			//cadastrar candidatos
			stub.cadastrarCandidatos("Ciro Gomes", 12);
			stub.cadastrarCandidatos("Datena", 17);
			stub.cadastrarCandidatos("Jair Bolsonaro", 51);
			stub.cadastrarCandidatos("Jo�o D�ria", 45);
			stub.cadastrarCandidatos("Lula", 13);
			stub.cadastrarCandidatos("S�rgio Moro", 66);
			stub.cadastrarCandidatos("Branco / Nulo", 0);
			
			System.out.println("\n=========== Verificar apura��o ===========\n");
			stub.apuracaoDeTodosVotos();			
					
			System.out.println("\n=========== consultar um candidato pelo seu n�mero ===========\n");
			System.out.println(stub.consultarCandidatos(45));			
			
			System.out.println("\n=========== consultar todos os candidatos ===========\n");
			stub.consultarCandidatos();
						
			System.out.println("\n=========== SIMULA��O - VOTA��O NA URNA ===========\n");
			for (int i = 0 ; i < 10000 ; i++) {
			List <Integer> rangeCandidatos = Arrays.asList(12 , 17, 51, 45, 13, 66, 0);
			Collections.shuffle(rangeCandidatos);			
			stub.receberVoto(rangeCandidatos.get(0),stub.consultarCandidatos(rangeCandidatos.get(0)));
			Thread.sleep(1000);
			}
			
		} catch (Exception e) {
			System.err.println("Excecao: " + e.toString());
			e.printStackTrace();
		}

	}
}
