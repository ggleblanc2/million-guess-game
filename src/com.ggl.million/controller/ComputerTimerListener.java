package com.ggl.million.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import com.ggl.million.model.Guess;
import com.ggl.million.model.MillionGuessesModel;
import com.ggl.million.view.MillionGuessesFrame;
import com.ggl.million.view.dialog.ComputerGuessDialog;

public class ComputerTimerListener implements ActionListener {
	
	private boolean firstTimeSwitch;
	
	private final List<Guess> guesses;
	
	private final MillionGuessesFrame view;
	
	private final MillionGuessesModel model;
	
	private final Random random;

	public ComputerTimerListener(MillionGuessesFrame view,
			MillionGuessesModel model, Random random) {
		this.view = view;
		this.model = model;
		this.random = random;
		this.guesses = new ArrayList<>();
		this.firstTimeSwitch = true;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String computerGuessString = null;
		int maxValue = 1_000_000;
		if (firstTimeSwitch) {
			int guessValue = random.nextInt(maxValue);
			computerGuessString = String.format("%06d", guessValue);
			firstTimeSwitch = false;
		} else {
			List<Guess> possibleGuesses = generatePossibleGuesses(maxValue);
			maxValue = possibleGuesses.size();
			int guessIndex = random.nextInt(maxValue);
			computerGuessString = possibleGuesses.get(guessIndex).getGuess();
		}
		
		String actualNumber = model.getNumberToGuess();
//		System.out.println(actualNumber);
		Guess guess = new Guess(computerGuessString, actualNumber);
		guesses.add(guess);
		
		String displayString = createDisplayString(computerGuessString,
				maxValue);
		
		model.addGuess(guess);
		view.updateControlPanel();
		
		Timer timer = (Timer) event.getSource();
		ComputerGuessDialog cgDialog = new ComputerGuessDialog(view, displayString);
		if (cgDialog.getReturnCode() != 0) {
			view.exitApplication();
		}
		
		if (guess.isGuessCorrect()) {
			view.getMainPanel().showImagePanel();
			view.getStartButton().setEnabled(true);
			timer.stop();
		}
	}

	private List<Guess> generatePossibleGuesses(int maxValue) {
		String computerGuessString;
		List<Guess> possibleGuesses = new ArrayList<>();
		Guess guess = null;
		for (int index = 0; index < maxValue; index++) {
			computerGuessString = String.format("%06d", index);
			boolean isValid = true;
			for (int jndex = 0; jndex < guesses.size(); jndex++) {
				Guess previousGuess = guesses.get(jndex);
				guess = new Guess(computerGuessString,
						previousGuess.getGuess());
				if (previousGuess.getCorrectPosition() != guess
						.getCorrectPosition()
						|| previousGuess.getCorrectDigits() != guess
								.getCorrectDigits()) {
					isValid = false;
					break;
				}
			}
			if (isValid) {
				possibleGuesses.add(guess);
			}
		}

		return possibleGuesses;
	}

	private String createDisplayString(String computerGuessString,
			int maxValue) {
		String displayString = "";
		if (maxValue > 1) {
			displayString += "<html>My guess is " + computerGuessString
					+ " from among<br>" + String.format("%,d", maxValue)
					+ " possible guesses";
		} else {
			displayString += "<html>My guess is " + computerGuessString
					+ " from among<br>" + String.format("%,d", maxValue)
					+ " possible guess";
		}
		
		return displayString;
	}

}
