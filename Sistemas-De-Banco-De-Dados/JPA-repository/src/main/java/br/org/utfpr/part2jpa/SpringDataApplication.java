package br.org.utfpr.part2jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.org.utfpr.part2jpa.entity.Departamento;
import br.org.utfpr.part2jpa.service.CrudDepartamentoService;
import br.org.utfpr.part2jpa.service.CrudFuncionarioService;
import br.org.utfpr.part2jpa.service.DepartamentoService;
import br.org.utfpr.part2jpa.service.RelatoriosService;


@EnableJpaRepositories
@ComponentScan(basePackages = {"br.org.utfpr.part2jpa"})
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	/**
	 * Classe principal da aplicação.
	 */
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataApplication.class);

	private Boolean system = true;	
	
	private final CrudDepartamentoService crudDepartamentoService;
	
	private final CrudFuncionarioService crudFuncionarioService;
	
	private final RelatoriosService relatoriosService;	
	
	private final DepartamentoService departamentoService;
		
	@Autowired
	public SpringDataApplication(CrudDepartamentoService crudDepartamentoService,
			CrudFuncionarioService crudFuncionarioService,
			RelatoriosService relatoriosService, DepartamentoService departamentoService ) {
		this.crudDepartamentoService = crudDepartamentoService;	
		this.crudFuncionarioService = crudFuncionarioService;		
		this.relatoriosService = relatoriosService;
		this.departamentoService = departamentoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Selecione a função desejada?");
			System.out.println("0 - Sair");
			System.out.println("1 - Departamento");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Relatorios");
			System.out.println("4 - Testar aplicação atividade05 parte 02");

			Integer function = scanner.nextInt();

			switch (function) {
				case 1:
					crudDepartamentoService.inicial(scanner);
					break;
				case 2:
					crudFuncionarioService.inicial(scanner);
					break;	
				case 3:
					relatoriosService.inicial(scanner);
					break;
				case 4:
					testarAplicacao();
				default:
					System.out.println("Finalizando");
					system = false;
					break;
			}
		}
	}

	private void testarAplicacao() {
		LOGGER.info("Iniciando teste da aplicação atividade 05 parte 02");
		popularDepartamentosH2();
		
	}

	private void popularDepartamentosH2() {
		List<String> listaDeNomesDepartamentos = new ArrayList<>();
		listaDeNomesDepartamentos.add("Fiscal");
		listaDeNomesDepartamentos.add("Financeiro");
		listaDeNomesDepartamentos.add("Contabil");
		listaDeNomesDepartamentos.add("Dpto Pessoal");
		listaDeNomesDepartamentos.add("TI");
		listaDeNomesDepartamentos.add("Diretória");
		listaDeNomesDepartamentos.add("Legal");
		listaDeNomesDepartamentos.add("Recursos Humanos");
		
		listaDeNomesDepartamentos.forEach(nomeDptos -> departamentoService.salvar(new Departamento(nomeDptos)));		
		
	}
}