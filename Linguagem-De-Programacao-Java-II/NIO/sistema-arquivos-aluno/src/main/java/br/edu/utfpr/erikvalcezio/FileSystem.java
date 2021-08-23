package br.edu.utfpr.erikvalcezio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Activity proposed in the discipline to learn
 * Java NIO 2 first version belongs to the course tutor Arthur Gregorio
 * purpose of the main class to service the file system
 *  
 * @version 1.0.0
 * @author Erik Eduardo Valcezio * 
 *
 */

public class FileSystem {
	
    /**
     * main directory, set the ROOT parameter value with root path to simulate the file system
     * Exemple: 
     * ..ROOT = 
     * "G:" + File.separator + "UTFPR" + File.separator
			+ "2 - Linguagem De Programação Java II 26_06_21 a 16_09_21" + File.separator
			+ "Bloco 2 - B2A1 - Sistema de arquivos" + File.separator + "hd" + File.separator;
     */
	
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
					System.out.print(currentPath.toFile().getCanonicalPath() + " $> ");
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
