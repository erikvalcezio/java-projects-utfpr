package br.utfpr.erikvalcezio.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class UsingNIO {

	private static final String MY_FILE = "C:" + File.separator + "temp" +  File.separator + "file-using-nio.txt";
	
	public UsingNIO(){
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
	
	public void writeFile() throws UnsupportedEncodingException, IOException {
		
		final RandomAccessFile file = new RandomAccessFile(MY_FILE, "rw");
		final FileChannel channel = file.getChannel();
		
		channel.write(ByteBuffer.wrap("Ola Mundo NIO".getBytes(StandardCharsets.UTF_8)));
		channel.close();
		file.close();
		
		System.out.println("Dados gravados no arquivo");
	}
	
	public void readFile() throws IOException {
		final RandomAccessFile file = new RandomAccessFile(MY_FILE, "rw");
		final FileChannel channelRead = file.getChannel();

		
		final ByteBuffer buffer = ByteBuffer.allocate((int) channelRead.size());

		channelRead.read(buffer);
		buffer.flip();

		for (int i = 0; i < channelRead.size() ; i++) {
			System.out.print((char) buffer.get());
		}

		channelRead.close();
		file.close();

	}
	
	public static void main(String [] args) {
		
		new UsingNIO();
		
	}
	
}
