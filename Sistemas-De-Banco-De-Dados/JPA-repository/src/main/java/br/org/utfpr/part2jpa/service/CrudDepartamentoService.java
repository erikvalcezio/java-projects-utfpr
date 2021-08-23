package br.org.utfpr.part2jpa.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.org.utfpr.part2jpa.entity.Departamento;
import br.org.utfpr.part2jpa.repository.DepartamentoRepository;

@Service
public class CrudDepartamentoService {
	/**
	 * Classe para os serviços com funcionalidade ao Departamento
	 * projeto inicial com melhorias a serem realizadas apenas para
	 * efeito da lista, para atividade02 de repositório representada DepartamentoService
	 */
	
	private Boolean system = true;		
	
	private final DepartamentoRepository departamentoRepository;	
	
	@Autowired
	public CrudDepartamentoService(@Qualifier("departamento") DepartamentoRepository departamentoRepository) {
		this.departamentoRepository = departamentoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Selecione a opção:");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar(Salvar)");
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
				visualizar();
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
		System.out.println("Nome do departamento");
		String nome = scanner.next();
		Departamento departamento = new Departamento();
		departamento.setNomeDepartamento(nome);
		departamentoRepository.save(departamento);
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Digite código do departamento:");
		Long codigoDepartamento = scanner.nextLong();		
		System.out.println("Digite novo nome do departamento:");
		String nome = scanner.next();		
		Departamento departamento = new Departamento();	
		departamento.setCodigoDepartamento(codigoDepartamento);
		departamento.setNomeDepartamento(nome);
		departamentoRepository.save(departamento);
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
		Iterable<Departamento> departamento = departamentoRepository.findAll();
		departamento.forEach(dpto -> System.out.println(departamento));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o código do departamento:");
		long id = scanner.nextInt();
		departamentoRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
}

