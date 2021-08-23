package org.utfpr.jparepositorypt2ativ05;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.utfpr.jparepositorypt2ativ05.entity.Departamento;
import org.utfpr.jparepositorypt2ativ05.entity.Funcionario;
import org.utfpr.jparepositorypt2ativ05.service.DepartamentoService;
import org.utfpr.jparepositorypt2ativ05.service.FuncionarioService;

@EnableJpaRepositories
@ComponentScan(basePackages = { "org.utfpr.jparepositorypt2ativ05" })
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	/**
	 * Classe principal da aplicação.
	 */

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataApplication.class);

	private Boolean system = true;	

	private final DepartamentoService departamentoService;

	private final FuncionarioService funcionarioService;

	private int massaTestePopularDados = 0;

	@Autowired
	public SpringDataApplication(DepartamentoService departamentoService, FuncionarioService funcionarioService) {		
		this.departamentoService = departamentoService;
		this.funcionarioService = funcionarioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try (Scanner scanner = new Scanner(System.in)) {
			while (system) {
				System.out.println("Selecione a função desejada?");
				System.out.println("0 - Sair");
				System.out.println("1 - Testar aplicação atividade05 parte 02");

				Integer function = scanner.nextInt();

				switch (function) {
				case 1:
					testarAplicacaoAtividade05();
				default:
					System.out.println("Finalizando");
					system = false;
					break;
				}
			}
			scanner.nextLine();
		}
	}
	
	/**
	 * Abrir H2 -> http://127.0.0.1:8080/test
	 */
	private void testarAplicacaoAtividade05() {
		//Scanner scanner = new Scanner(System.in);
		
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
		
		LOGGER.info("\n Listar os funcionários que tem salário maior que um determinado. \n {}", funcionarioService.buscaPorFuncionarioComMaiorSalarioQ(103.00));
		
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

	@Bean
	public void popularBaseH2() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		List<Departamento> listaDeNomesDepartamentos = new ArrayList<>();
		listaDeNomesDepartamentos.add(new Departamento("Fiscal"));
		listaDeNomesDepartamentos.add(new Departamento("Financeiro"));
		listaDeNomesDepartamentos.add(new Departamento("Contabil"));
		listaDeNomesDepartamentos.add(new Departamento("Dpto Pessoal"));
		listaDeNomesDepartamentos.add(new Departamento("TI"));
		listaDeNomesDepartamentos.add(new Departamento("Diretória"));
		listaDeNomesDepartamentos.add(new Departamento("Legal"));
		listaDeNomesDepartamentos.add(new Departamento("Recursos Humanos"));

		listaDeNomesDepartamentos.forEach(nomeDptos -> departamentoService.salvar(nomeDptos));

		listaDeNomesDepartamentos.forEach(nomeDptos -> funcionarioService
				.salvar(new Funcionario("NomeFuncionario".concat(String.format("%02d", massaTestePopularDados)),
						"039.258.898-".concat(String.format("%02d", massaTestePopularDados)), massaTestePopularDados,
						100 + massaTestePopularDados, "caxias".concat(String.format("%02d", massaTestePopularDados++)),
						LocalDate.parse((String.format("%02d", massaTestePopularDados)).concat("/10/2021"), formatter),
						nomeDptos)));
	}

}