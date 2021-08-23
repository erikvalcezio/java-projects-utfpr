package br.org.utfpr.part2jpa.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.org.utfpr.part2jpa.entity.Funcionario;
import br.org.utfpr.part2jpa.repository.FuncionarioRepositoryPaging;


@Service
public class RelatoriosService {
	/**
	 * Relatórios da aplicação apenas para
	 * efeito da lista, para atividade02 de repositório representada FuncionarioService e DepartamentoService
	 */

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepositoryPaging pagingFuncionarioRepository;
	
	public RelatoriosService(FuncionarioRepositoryPaging pagingFuncionarioRepository) {
		this.pagingFuncionarioRepository = pagingFuncionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual relatório deseja executar");
			System.out.println("0 - Sair");
			
			/*• Listar um funcionário pelo seu nome e quantidade de dependentes utilizando consulta
			por palavras-chaves*/
			System.out.println("1 - Busca funcionario nome e quantidade de dependentes:");
			
			System.out.println("2 - Busca funcionario nome, data contratacao e salario maior");
			
			System.out.println("3 - Busca funcionario data contratacao");
			
			/*• Listar todos os funcionários de um determinado departamento por JPQL via @Query*/
			System.out.println("4 - Busca funcionario por departamento #EM MANUTENÇÃO#"); 
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			case 4:
				buscaFuncionarioPorDepartamento(scanner);
				break;
			default:
				system = false;
				break;
			}
			
		}
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.next();
		List<Funcionario> list = pagingFuncionarioRepository.findByNomeFuncionario(nome);
		
		list.forEach(System.out::println);
		
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome deseja pesquisar?");
		String nome = scanner.next();
		
		System.out.println("Qual data contratacao deseja pesquisar?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.println("Qual salario deseja pesquisar?");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = pagingFuncionarioRepository
				.findNomeFuncionarioSalarioMaior(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual data contratacao deseja pesquisar");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = pagingFuncionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
	private void buscaFuncionarioPorDepartamento(Scanner scanner) {
		System.out.println("Digite o nome do departamento:");
		String nomeDepartamento = scanner.next();		
		
		List<Funcionario> list = pagingFuncionarioRepository.findPorNomeDepartamento(nomeDepartamento);
		list.forEach(System.out::println);
	}
	
}
