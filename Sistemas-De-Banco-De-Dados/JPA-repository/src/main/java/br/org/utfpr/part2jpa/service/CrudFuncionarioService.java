package br.org.utfpr.part2jpa.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.org.utfpr.part2jpa.entity.Departamento;
import br.org.utfpr.part2jpa.entity.Funcionario;
import br.org.utfpr.part2jpa.repository.DepartamentoRepository;
import br.org.utfpr.part2jpa.repository.FuncionarioRepositoryPaging;

@Service
public class CrudFuncionarioService {
	/**
	 * Classe para os serviços com funcionalidade ao Funcionario
	 * projeto inicial com melhorias a serem realizadas  a serem realizadas apenas para
	 * efeito da lista, para atividade02 de repositório representada FuncionarioService
	 */

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	
	private final FuncionarioRepositoryPaging paginFuncionarioRepository;
	
	private final DepartamentoRepository departamentoRepository;
	
	@Autowired
	public CrudFuncionarioService(FuncionarioRepositoryPaging paginFuncionarioRepository, DepartamentoRepository departamentoRepository ) {
		this.paginFuncionarioRepository = paginFuncionarioRepository;
		this.departamentoRepository = departamentoRepository;
	}

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Selecione a opção:");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Excluir");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar(scanner);
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void salvar(Scanner scanner) {
		System.out.println("Digite o nome:");
		String nome = scanner.next();

		System.out.println("Digite o cpf:");
		String cpf = scanner.next();
		
		System.out.println("Quantidade de dependentes:");
		Integer NumeroDependente = scanner.nextInt();

		System.out.println("Digite o salario:");
		Double salario = scanner.nextDouble();
		
		System.out.println("Digite o cargo:");
		String cargo = scanner.next();

		System.out.println("Digite a data de contracao #dd/MM/yyyy# :");
		String dataContratacao = scanner.next();

		System.out.println("Digite o código do departamento:");
		Long codigoDepartamento = scanner.nextLong();

		Funcionario funcionario = new Funcionario();
		funcionario.setNomeFuncionario(nome);
		funcionario.setCpf(cpf);
		funcionario.setQuantidadeDependente(NumeroDependente);
		funcionario.setSalario(salario);
		funcionario.setCargo(cargo);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Departamento> departamento = departamentoRepository.findById(codigoDepartamento);
		funcionario.setDepartamento(departamento.get());

		paginFuncionarioRepository.save(funcionario);
		System.out.println("Salvo");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o código do departamento:");
		Long id = scanner.nextLong();

		System.out.println("Digite o nome:");
		String nome = scanner.next();

		System.out.println("Digite o cpf:");
		String cpf = scanner.next();
		
		System.out.println("Quantidade de dependentes:");
		Integer NumeroDependente = scanner.nextInt();

		System.out.println("Digite o salario:");
		Double salario = scanner.nextDouble();
		
		System.out.println("Digite o cargo:");
		String cargo = scanner.next();

		System.out.println("Digite a data de contracao #dd/MM/yyyy#:");
		String dataContratacao = scanner.next();

		System.out.println("Digite o código do departamento:");
		Long departamentoId = scanner.nextLong();

		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNomeFuncionario(nome);
		funcionario.setCpf(cpf);
		funcionario.setQuantidadeDependente(NumeroDependente);
		funcionario.setSalario(salario);
		funcionario.setCargo(cargo);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Departamento> departamento = departamentoRepository.findById(departamentoId);
		funcionario.setDepartamento(departamento.get());

		paginFuncionarioRepository.save(funcionario);
		System.out.println("Alterado");
	}

	private void visualizar(Scanner scanner) {
		System.out.println("Qual pagina voce deseja visualizar?");
		Integer page = scanner.nextInt();

		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Funcionario> funcionarios = paginFuncionarioRepository.findAll(pageable);

		System.out.println(funcionarios);
		System.out.println("Pagina atual " + funcionarios.getNumber());
		System.out.println("Total elemento " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		long id = scanner.nextInt();
		paginFuncionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}

}
