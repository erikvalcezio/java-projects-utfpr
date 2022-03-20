package br.edu.utfpr.erikvalcezio.service;

import java.util.Random;

public class MyRandomNumber {

	private static Integer numberRandom;

	public int nextRandomNumber(int begin, int end) throws IntervaloInvalidoException {
		
		if (begin >= end) {			
			throw new IntervaloInvalidoException("begin eh maior ou igual end");
		}		
		if (begin < 0) {
			throw new IntervaloInvalidoException("begin eh menor que zero");
		} 			

		end ++;
		
		Random random = new Random();

		int genNumber;

		do {

			genNumber = random.ints(begin, end).findFirst().getAsInt();

			if (numberRandom == null) {

				MyRandomNumber.numberRandom = genNumber;
			}

		} while (MyRandomNumber.numberRandom == genNumber);
		
		MyRandomNumber.numberRandom = genNumber;

		return genNumber;
	}
}

class IntervaloInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public IntervaloInvalidoException(String msg) {
		super(msg);
	}
}


