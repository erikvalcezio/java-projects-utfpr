package org.utfpr.jparepository;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.utfpr.jparepository.entity.Departamento;
import org.utfpr.jparepository.entity.Funcionario;
import org.utfpr.jparepository.service.DepartamentoService;
import org.utfpr.jparepository.service.FuncionarioService;

public class Atividade07 {	
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
	private static final Logger LOGGER = LoggerFactory.getLogger(Atividade07.class);
	
	public static void testarAplicacaoAtividade07(FuncionarioService funcionarioService, DepartamentoService departamentoService ) {		
		
		LOGGER.info("Iniciando teste da aplicação atividade 06");
			
		LOGGER.info("\nBusca todos funcionários com findALL() \n {}", funcionarioService.buscaTodosFuncionarios().toString());
		
		/*
		 * 1. Criar um método na classe de serviço de departamento para salvar um departamento, associar esse departamento a um funcionário e salvar esse funcionário em um mesmo controle de transação.
		 */		
		
		LOGGER.info(
				"\n Salvar um departamento, associar esse departamento a um funcionário e salvar esse funcionário :");
				departamentoService.criarDepartamentoAtualizaFuncionario(new Funcionario("Zeus","12345678911", 8, 999999.00, "Deus", LocalDate.parse("09/10/2021", formatter)), new Departamento("Design Thinking"));
	}


}
