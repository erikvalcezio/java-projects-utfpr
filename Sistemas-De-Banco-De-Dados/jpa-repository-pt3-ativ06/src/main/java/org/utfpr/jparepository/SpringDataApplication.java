package org.utfpr.jparepository;

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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.utfpr.jparepository.entity.Departamento;
import org.utfpr.jparepository.entity.Funcionario;
import org.utfpr.jparepository.service.DepartamentoService;
import org.utfpr.jparepository.service.FuncionarioService;

@EnableJpaRepositories
@ComponentScan(basePackages = { "org.utfpr.jparepository" })
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	/**
	 * Classe principal da aplicação. Método popularBaseH2() popula a base de dados
	 * h2 para teste da aplicação caso necessário (atividade05)
	 * 
	 */

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataApplication.class);

	private Boolean system = true;

	private final DepartamentoService departamentoService;

	private final FuncionarioService funcionarioService;

	private int massaTestePopularDados = 0;
	
	private static Scanner scanner = new Scanner(System.in);

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
		 
			while (system) {
				System.out.println("Selecione a função desejada?");
				System.out.println("0 - Sair");
				System.out.println("1 - Testar aplicação atividade05 parte 02");
				System.out.println("2 - Testar aplicação atividade06");

				Integer function = scanner.nextInt();

				switch (function) { 
				case 1:
					Atividade05.testarAplicacaoAtividade05(funcionarioService, departamentoService);
				case 2:
					Atividade06.testarAplicacaoAtividade06(funcionarioService, departamentoService);
				default:
					System.out.println("Finalizando");
					system = false;
					scanner.close();
					break;
				}
			}		
	}

	/**
	 * habilitar @Bean somente em testes com H2
	 */
	// @Bean
	public void popularBaseH2() {

		LOGGER.info("Populando H2 para teste da aplicação \nPOPULANDO BANCO DE DADOS ");

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