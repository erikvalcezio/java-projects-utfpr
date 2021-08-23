package br.eti.arthurgregorio.sistemarquivos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader{
	
	private Path path;
	private String fileName;
	
	public FileReader(Path path, String fileName) {
		this.path = path;
		this.fileName = fileName;
	}
	
	public void read() throws IOException {
		isValidFile(this.path, this.fileName);
		Files.readAllLines(this.path.resolve(this.fileName)).forEach(System.out::println);
	}
	
	public boolean isValidFile(Path path, String fileName) throws IOException {		
		return Files.probeContentType(path.resolve(fileName)).contains("text");
	}

}
