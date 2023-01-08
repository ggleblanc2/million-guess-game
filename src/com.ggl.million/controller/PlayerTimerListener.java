package com.ggl.million.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.ggl.million.model.Guess;
import com.ggl.million.model.MillionGuessesModel;
import com.ggl.million.view.MillionGuessesFrame;
import com.ggl.million.view.dialog.PlayerGuessDialog;

public class PlayerTimerListener implements ActionListener {
	
	private final MillionGuessesFrame view;
	
	private final MillionGuessesModel model;

	public PlayerTimerListener(MillionGuessesFrame view,
			MillionGuessesModel model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Timer timer = (Timer) event.getSource();
		PlayerGuessDialog pgDialog = new PlayerGuessDialog(view);
		if (pgDialog.getReturnCode() != 0) {
			view.getMainPanel().showImagePanel();
			view.getStartButton().setEnabled(true);
			timer.stop();
			return;
		}
		
		String playerGuessString = pgDialog.getNumberField().getText();
		String guessString = model.getNumberToGuess();
		Guess guess = new Guess(playerGuessString, guessString);
//		System.out.println(playerGuessString + " " + guessString + " " +
//				+ guess.getCorrectPosition() + " " + guess.getCorrectDigits());
		model.addGuess(guess);
		view.updateControlPanel();
		
		if (guess.isGuessCorrect()) {
			view.getMainPanel().showImagePanel();
			view.getStartButton().setEnabled(true);
			timer.stop();
		}
	}
	


}
