package com.ggl.million.model;

public class Guess {
	
	private final int correctPosition;
	private final int correctDigits;
	
	private final String guess;

	public Guess(String guess, String numberToGuess) {
		this.guess = guess;
		this.correctPosition = calculateCorrectPosition(guess, numberToGuess);
		this.correctDigits = calculateCorrectDigits(guess, numberToGuess);
	}
	
	private int calculateCorrectPosition(String guess, String numberToGuess) {
		int count = 0;
		for (int index = 0; index < guess.length(); index++) {
			if (guess.charAt(index) == numberToGuess.charAt(index)) {
				count++;
			}
		}
		
		return count;
	}
	
	private int calculateCorrectDigits(String guess, String numberToGuess) {
		char[] numberToGuessChars = numberToGuess.toCharArray();
		char[] guessChars = guess.toCharArray();
		for (int index = 0; index < guess.length(); index++) {
			if (guess.charAt(index) == numberToGuess.charAt(index)) {
				guessChars[index] = (char) 0x0;
				numberToGuessChars[index] = (char) 0x1;
			}
		}
		
		int count = 0;
		for (int index = 0; index < numberToGuess.length(); index++) {
			for (int jndex = 0; jndex < guess.length(); jndex++) {
				if (index != jndex) {
					if (guessChars[jndex] == numberToGuessChars[index]) {
						count++;
						guessChars[jndex] = (char) 0x0;
						numberToGuessChars[index] = (char) 0x1;
					}
				}
			}
		}
		
		return count;
	}
	
	public boolean isGuessCorrect() {
		return (correctPosition == guess.length()) && (correctDigits == 0);
	}

	public int getCorrectPosition() {
		return correctPosition;
	}

	public int getCorrectDigits() {
		return correctDigits;
	}

	public String getGuess() {
		return guess;
	}
	
}
