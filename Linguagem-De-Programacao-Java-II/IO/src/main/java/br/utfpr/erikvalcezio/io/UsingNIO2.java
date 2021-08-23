package br.utfpr.erikvalcezio.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsingNIO2 {

	private static final String MY_FILE = "C:" + File.separator + "temp" +  File.separator + "file-using-nio2.txt";
	
	public UsingNIO2(){
		try {
			writeFile();			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("--------------------------------------------");
		
		try {
			readFile();			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void writeFile() throws IOException {
		
		final Path path = Paths.get(MY_FILE);
		Files.writeString(path, "Ola Mundo NIO2");	
		
		System.out.println("Dados gravados no arquivo");
	}
	
	public void readFile() throws IOException {
		
		final Path path = Paths.get(MY_FILE);
		Files.readAllLines(path).forEach(System.out::println);		

	}
	
	public static void main(String [] args) {
		
		new UsingNIO2();
		
	}
	
}
