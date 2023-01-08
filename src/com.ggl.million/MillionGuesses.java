package com.ggl.million;

import javax.swing.SwingUtilities;

import com.ggl.million.model.MillionGuessesModel;
import com.ggl.million.view.MillionGuessesFrame;

public class MillionGuesses implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MillionGuesses());
	}

	@Override
	public void run() {
		new MillionGuessesFrame(new MillionGuessesModel());
	}

}
