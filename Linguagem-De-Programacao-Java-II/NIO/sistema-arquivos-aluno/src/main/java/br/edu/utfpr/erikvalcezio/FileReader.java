package br.edu.utfpr.erikvalcezio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**  
* 
* @version 1.0.0
* @author Erik Eduardo Valcezio 
*
*/

public class FileReader {

	private Path path;
	private String fileName;

	public FileReader(Path path, String fileName) {
		this.path = path;
		this.fileName = fileName;
	}

	public boolean isValidFile() throws IOException {
		return Files.probeContentType(path.resolve(this.fileName)).contains("text");
	}

	public void read() throws IOException{	

		if (!isValidFile())
			throw new UnsupportedOperationException("não há suporte ao arquivo");

		try {
			Files.readAllLines(this.path.resolve(this.fileName)).forEach(System.out::println);
		} catch (Exception e) {
			throw new UnsupportedOperationException("ainda não há suporte para o arquivo");
		}
	}
}
