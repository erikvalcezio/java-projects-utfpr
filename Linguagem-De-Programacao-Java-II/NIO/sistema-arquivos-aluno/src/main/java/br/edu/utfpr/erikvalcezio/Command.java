package br.edu.utfpr.erikvalcezio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.nio.file.attribute.BasicFileAttributes;

/**  
* 
* @version 1.0.0
* @author Erik Eduardo Valcezio
*/

public enum Command {

	LIST() {
		@Override
		boolean accept(String command) {
			final var commands = command.split(" ");
			return commands.length > 0 && commands[0].startsWith("LIST") || commands[0].startsWith("list");
		}

		@Override
		Path execute(Path path) {

			try {
				Files.list(path).forEach(ls -> System.out.println(ls.toFile().getName()));
			} catch (Exception ex) {
				throw new UnsupportedOperationException("não foi possível listar o diretório");
			}
			return path;
		}
	},
	SHOW() {
		private String[] parameters = new String[] {};

		@Override
		void setParameters(String[] parameters) {
			this.parameters = parameters;
		}

		@Override
		boolean accept(String command) {
			final var commands = command.split(" ");
			return commands.length > 0 && commands[0].startsWith("SHOW") || commands[0].startsWith("show");
		}

		@Override
		Path execute(Path path) {

			try {
				if (parameters.length != 1 && parameters[1] != null && !parameters[1].trim().isBlank()
						&& Files.isDirectory(path))
					new FileReader(path, parameters[1]).read();
				else
					throw new UnsupportedOperationException(
							"Informe um paramêtro correto para comando: SHOW <nome do arquivo>");
			} catch (Exception ex) {
				throw new UnsupportedOperationException(ex.getMessage());
			}
			return path;
		}
	},
	BACK() {
		@Override
		boolean accept(String command) {
			final var commands = command.split(" ");
			return commands.length > 0 && commands[0].startsWith("BACK") || commands[0].startsWith("back");
		}

		@Override
		Path execute(Path path) {

			try {
				if (path.endsWith(Path.of(FileSystem.ROOT)))
					throw new UnsupportedOperationException("Diretótio Raiz: " + path.toFile().getAbsolutePath());
			} catch (Exception ex) {
				throw new UnsupportedOperationException(
						ex.getMessage() + "\nNão é possível voltar ao diretótio anterior.");
			}
			return path.getParent();
		}
	},
	OPEN() {
		private String[] parameters = new String[] {};

		@Override
		void setParameters(String[] parameters) {
			this.parameters = parameters;
		}

		@Override
		boolean accept(String command) {
			final var commands = command.split(" ");
			return commands.length > 0 && commands[0].startsWith("OPEN") || commands[0].startsWith("open");
		}

		@Override
		Path execute(Path path) throws IOException {
			Optional<Path> newPath = Optional.of(path);
			try {
				if (parameters.length != 1 && parameters[1] != null && !parameters[1].trim().isBlank()
						&& Files.isDirectory(path.resolve(parameters[1]))) {
					/**
					 * then you can browse the root directory hierarchy.
					 */
					newPath = Files.list(path).filter(ls -> ls.endsWith(parameters[1])).map(map -> map.getFileName())
							.findFirst();

				} else
					throw new UnsupportedOperationException("Erro: diretório inválido, use comando OPEN <diretório>");

			} catch (Exception ex) {
				throw new UnsupportedOperationException(ex.getMessage());
			}

			if (newPath.isEmpty())
				throw new UnsupportedOperationException("Só é permitido navegar na Raiz da hirarquia do diretório");

			return path.resolve(newPath.get());
		}

	},

	DETAIL() {

		private String[] parameters = new String[] {};

		@Override
		void setParameters(String[] parameters) {
			this.parameters = parameters;
		}

		@Override
		boolean accept(String command) {
			final var commands = command.split(" ");
			return commands.length > 0 && commands[0].startsWith("DETAIL") || commands[0].startsWith("detail");
		}

		@Override
		Path execute(Path path) {
			Path pathNew = path;

			if (parameters.length != 1 && parameters[1] != null && !parameters[1].trim().isBlank()) {

				if (path.toFile().getName().equals(parameters[1])) {
					pathNew = path;
				} else {
					pathNew = (Files.isDirectory(path.resolve(parameters[1]))
							|| Files.isReadable(path.resolve(parameters[1])) ? path.resolve(parameters[1]) : null);
				}

			} else {
				throw new UnsupportedOperationException("Comando inválido use DETAIL <nome do arquivo ou diretório>");
			}

			if (pathNew != null) {
				try {

					BasicFileAttributes attrs = Files.readAttributes(pathNew, BasicFileAttributes.class);
					System.out.format("Is a directory? [%s]\nSize [%d]\nCreated [%s]\nLast Acess Time [%s]\n",
							attrs.isDirectory(), attrs.size(), attrs.creationTime(), attrs.lastAccessTime());
				} catch (Exception ex) {
					throw new UnsupportedOperationException("Erro ao detalhar os atributos");
				}

			}
			return path;
		}

	},

	EXIT() {

		@Override
		boolean accept(String command) {
			final var commands = command.split(" ");
			return commands.length > 0 && commands[0].startsWith("EXIT") || commands[0].startsWith("exit");
		}

		@Override
		Path execute(Path path) {
			System.out.print("Saindo...");
			return path;
		}

		@Override
		boolean shouldStop() {
			return true;
		}

	};

	abstract Path execute(Path path) throws IOException;

	abstract boolean accept(String command);

	void setParameters(String[] parameters) {
	}

	boolean shouldStop() {
		return false;
	}

	public static Command parseCommand(String commandToParse) {

		if (commandToParse.isBlank()) {
			throw new UnsupportedOperationException("Type something...");
		}

		final var possibleCommands = values();

		for (Command possibleCommand : possibleCommands) {
			if (possibleCommand.accept(commandToParse)) {
				possibleCommand.setParameters(commandToParse.split(" "));
				return possibleCommand;
			}
		}

		throw new UnsupportedOperationException("Can't parse command [%s]".formatted(commandToParse));
	}
}
