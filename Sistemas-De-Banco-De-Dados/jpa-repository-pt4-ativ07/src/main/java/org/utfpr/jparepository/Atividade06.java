package org.utfpr.jparepository;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.utfpr.jparepository.service.DepartamentoService;
import org.utfpr.jparepository.service.FuncionarioService;

public class Atividade06 {	
	/**
	 * Facilitar a correção da atividade 05, base de dados MariaDb está no arquivo do projeto CRUD_MariaDB_func_dpto_view_procedure.sql 
	 */
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Atividade06.class);
	
	public static void testarAplicacaoAtividade06(FuncionarioService funcionarioService, DepartamentoService departamentoService ) {		
		
		LOGGER.info("Iniciando teste da aplicação atividade 06");
			
		LOGGER.info("\nBusca todos funcionários com findALL() \n {}", funcionarioService.buscaTodosFuncionarios().toString());
		
		/*
		 * 1. Uma procedure que aumenta o salário de todos os funcionários em X porcento, onde X é um número inteiro.
		 */
		
		/**
		 * CONSULTAR NO ARQUIVO DO PROJETO A CRIAÇÃO DA PROCEDURE proc_aumenta_salario NO ARQUIVO CRUD_MariaDB_func_dpto_view_procedure.sql 
		 */
		
		LOGGER.info("\n Aumenta o salário de todos os funcionários em porcentagem X \n  {}", funcionarioService.aumentaSalarioTodosFuncionarios(10));
				
		/*
		 * 2. Uma consulta que lista todos os funcionários de um determinado departamento que não possuam dependentes utilizando parâmetros nomeados.
		 */
		
		LOGGER.info(
				"\n Uma consulta que lista todos os funcionários de um determinado departamento que não possuam dependentes \n  {}",
				funcionarioService.buscaPorDepartamentoAndQuantidadeDependente("Recursos Humanos", 0 ));				
				
		/*
		 * 3. Uma instrução de update que troca todos os funcionários de um determinado departamento para outro departamento utilizando a anotação @Modifying.
		 */
		
		LOGGER.info(
				"\n  Uma instrução de update que troca todos os funcionários de um determinado departamento para outro (Ciência para Tecnologia) \n {} ",
				funcionarioService.atualizarFuncionarioDepartamento("Tecnologia", "Ciencia") > 0 ? "Departamento alterado"
						: "Não houve alteração de Departamento");
		/*
		 * 4. Uma instrução de delete que exclui todos os funcionários de um determinado departamento utilizando a anotação @Modifying.
		 */
		LOGGER.info(
				"\n Uma instrução de delete que exclui todos os funcionários de um determinado departamento, quantidade excluida foi  {} ",
				funcionarioService.deletarFuncionarioDepartamento("Desenvolvimento"));
	}


}
