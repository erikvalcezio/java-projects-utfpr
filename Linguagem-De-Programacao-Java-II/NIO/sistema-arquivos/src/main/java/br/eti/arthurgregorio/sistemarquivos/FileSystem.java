package br.eti.arthurgregorio.sistemarquivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileSystem {

	// FIXME ajustar para o caminho no PC do aluno
	
	public static final String ROOT = "G:" + File.separator + "UTFPR" + File.separator
			+ "2 - Linguagem De Programação Java II 26_06_21 a 16_09_21" + File.separator
			+ "Bloco 2 - B2A1 - Sistema de arquivos" + File.separator + "hd" + File.separator;

	public FileSystem() {
		executar();
	}

	private boolean isValidDirectory() {
		return Files.isDirectory(Path.of(ROOT)) && Files.isReadable(Path.of(ROOT));
	}

	private void executar() {
		if (!isValidDirectory()) {
			throw new UnsupportedOperationException("validate if it is directory and if there is permission");
		}

		try (final Scanner scanner = new Scanner(System.in)) { // closed scanner

			System.out.println("Bem vindo ao sistema de arquivos!");
			

			var stop = false;

			var currentPath = Paths.get(ROOT);

			while (!stop) {
				try {
					System.out.print("$> ");
					final var command = Command.parseCommand(scanner.nextLine());
					currentPath = command.execute(currentPath);
					stop = command.shouldStop();
				} catch (UnsupportedOperationException | IOException ex) {
					System.out.printf("%s", ex.getMessage()).println();
				}
			}
		}
		System.out.println("Sistema de arquivos encerrado.");
	}

	public static void main(String[] args) throws IOException {
		new FileSystem();
		
		
	}
}
