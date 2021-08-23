package org.utfpr.jparepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.utfpr.jparepository.service.DepartamentoService;
import org.utfpr.jparepository.service.FuncionarioService;

public class Atividade05 {	
	/**
	 * Facilitar a correção da atividade 05
	 */
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Atividade05.class);
	
	public static void testarAplicacaoAtividade05(FuncionarioService funcionarioService, DepartamentoService departamentoService ) {		
		
		LOGGER.info("Iniciando teste da aplicação atividade 05 parte 02");
			
		LOGGER.info("\nBusca todos funcionários com findALL() \n {}", funcionarioService.buscaTodosFuncionarios().toString());

		/*
		 * Listar um funcionário pelo seu nome e quantidade de dependentes utilizando
		 * consulta por palavras-chaves.
		 */
		
		LOGGER.info("\nLista um funcionário pelo seu nome e quantidades "
				+ "de dependentes\n {} ", funcionarioService.buscaPorNomeFuncQtdDependente("NomeFuncionario02", 2).toString());		
		
		/*
		 * Listar todos os funcionários de um determinado departamento por JPQL
		 * via @Query.
		 */
		
		LOGGER.info("\nLista todos os funcionários de um determinado departamento por JPQL \n {}",
				funcionarioService.buscaTodosFuncionariosPorDepartamento("Financeiro").toString()); 
		
		/*
		 * Listar o primeiro departamento cadastrado. 
		 */		 
		 
		LOGGER.info("\nLista o primeiro departamento cadastrado \n {}", departamentoService.buscaPorPrimeiroDepartamentoCadastrado());
		
		/*
		 * Listar o primeiro funcionário que tem o maior salário.
		 */
		 
		LOGGER.info("\n Listar o primeiro funcionário que tem o maior salário. \n {}", funcionarioService.buscaPorFuncionarioComMaiorSalario());
		
		/*
		 * Listar os 3 (três) primeiros funcionários que tem os maiores salários. 
		 */
		 
		LOGGER.info("\n Listar os 3 (três) primeiros funcionários que tem os maiores salários. \n {}", funcionarioService.buscaPorFuncionarioComTresMaioresSalario());
		
		/*
		 * Listar os funcionários que não tem dependentes em ordem crescente de nome por JPQL via @Query.
		 */
		
		LOGGER.info("\n Listar os funcionários que não tem dependentes em ordem crescente de nome por JPQL. \n {}", funcionarioService.buscaPorFuncionarioSemDependente());
		
		/*
		 * Listar os funcionários que tem salário maior que um determinado valor por JPQL sobrescrevendo
		 * palavras-chaves @Query.
		 */
		
		LOGGER.info("\n Listar os funcionários que tem salário maior que um determinado valor 103.00. \n {}", funcionarioService.buscaPorFuncionarioComMaiorSalarioQ(103.00));
		
		/*
		 * Listar os funcionários que tem salário maior que um
		 * determinado valor por @Query com native query.		 
		 */
		
		LOGGER.info("\n Listar os funcionários que tem salário maior que um determinado com native query. \n {}", funcionarioService.buscaPorFuncionarioComMaiorSalarioQN(105.00));
				
		/*
		 * Alterar a classe Funcionario e criar uma consulta para listar os funcionários com uma determinada
		 * quantidade de dependentes por @NamedQuery.
		 */
		
		LOGGER.info("\n Listar os funcionários com uma determinada quantidade de dependentes por @NamedQuery. \n {}", funcionarioService.buscaPorFuncionarioComQuantidadeDependentes(4));
		
		/*
		 * Alterar a classe Funcionario e criar uma consulta para listar os funcionários que contenham em qualquer
		 * parte do seu nome um determinado nome por @NamedNativeQuery.
		 */
		
		LOGGER.info("\n Listar os funcionários que contenham em qualquer parte do seu nome um determinado valor @NamedNativeQuery \n {}",
				funcionarioService.buscaPorNomeFuncionarioParcial("00").toString());
		
		/*
		 * Alterar a interface FuncionarioRepository para utilizar a @NamedQuery
		 * e @NamedNativeQuery realizadas nas questões 9 e 10 (respectivamente).
		 */

	}


}
