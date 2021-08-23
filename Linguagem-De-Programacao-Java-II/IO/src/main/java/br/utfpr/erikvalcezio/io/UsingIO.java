package br.utfpr.erikvalcezio.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UsingIO {

	private static final String MY_FILE = "C:" + File.separator + "temp" +  File.separator + "file-using-io.txt";

	public UsingIO() {

		try {
			final File file = new File(MY_FILE);

			if (!file.exists()) {
				file.createNewFile();
				System.out.println(file.exists());
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
	public UsingIO(int um) {

		try {
			writeFile();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		readFile();

	}
	
	private void writeFile() throws IOException {
		
		final File file = new File(MY_FILE);
		
		boolean fileIsCreated = false;
		
		if (!file.exists()) {
			fileIsCreated = file.createNewFile();
		}
		
		if (fileIsCreated || file.exists()) {
			final OutputStream output = new FileOutputStream(file);
			
						
			output.write("Ola Mundo IO".getBytes("UTF-8"));
			output.close();
			
		}
		
		System.out.println("Dados gravados no arquivo");
		
	}

	private void readFile() {
		
		//InputStream
		try (final InputStream input = new FileInputStream(MY_FILE)) {
			int content;
			while ((content = input.read()) != -1) {
				System.out.print((char) content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n");
		
		//FileReader
		try (final FileReader reader = new FileReader(MY_FILE)) {
			int content = reader.read();

			while (content != -1) {
				System.out.print((char) content);
				content = reader.read();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void main(String[] args) {
		new UsingIO(1);
		
	}

}
