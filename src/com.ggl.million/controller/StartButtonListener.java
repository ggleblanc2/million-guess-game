package com.ggl.million.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import com.ggl.million.model.MillionGuessesModel;
import com.ggl.million.view.MillionGuessesFrame;
import com.ggl.million.view.dialog.PlayerSecretNumberDialog;
import com.ggl.million.view.dialog.WhoGuessesDialog;

public class StartButtonListener implements ActionListener {

	private final MillionGuessesFrame view;

	private final MillionGuessesModel model;

	private final Random random;

	public StartButtonListener(MillionGuessesFrame view,
			MillionGuessesModel model) {
		this.view = view;
		this.model = model;
		this.random = new Random();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		model.resetGuesses();
		view.getMainPanel().showTablePanel();
		view.getStartButton().setText("Restart Game");
		view.getStartButton().setEnabled(false);
		processDialogs();

		if (model.isComputerGuessing()) {
			Timer timer = new Timer(5000,
					new ComputerTimerListener(view, model, random));
			timer.setInitialDelay(0);
			timer.start();
		} else {
			int guessValue = random.nextInt(1_000_000);
			String guessString = String.format("%06d", guessValue);
//			System.out.println(guessString);
			model.setNumberToGuess(guessString);
			Timer timer = new Timer(5000, new PlayerTimerListener(view, model));
			timer.setInitialDelay(0);
			timer.start();
		}
	}

	public void processDialogs() {
		WhoGuessesDialog wgDialog = new WhoGuessesDialog(view, model);
		if (wgDialog.getReturnCode() != 0) {
			view.exitApplication();
		}

		if (model.isComputerGuessing()) {
			PlayerSecretNumberDialog psnDialog = new PlayerSecretNumberDialog(
					view, model);
			if (psnDialog.getReturnCode() != 0) {
				view.exitApplication();
			}
		}
	}

}
