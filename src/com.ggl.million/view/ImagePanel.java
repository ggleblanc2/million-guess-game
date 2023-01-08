package com.ggl.million.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.ggl.million.model.MillionGuessesModel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final MillionGuessesModel model;

	public ImagePanel(MillionGuessesModel model, Dimension d) {
		this.model = model;
		this.setPreferredSize(d);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		createGameOverImage(g);
	}
	
	private void createGameOverImage(Graphics g) {
		String text = (model.isComputerGuessing()) ? "The computer " : "You ";
		text += "guessed the number " + model.getNumberToGuess();
		Font font = new Font(Font.DIALOG, Font.BOLD, 20);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.GREEN);
		g.setFont(font);
		FontMetrics fontMetrics = g.getFontMetrics();
		int px = (int) (getWidth() / 2
				- fontMetrics.getStringBounds(text, g).getWidth() / 2);
		int py = (int) (getHeight() / 2
				+ (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2);
		g.drawString(text, px, py);
	}

}
