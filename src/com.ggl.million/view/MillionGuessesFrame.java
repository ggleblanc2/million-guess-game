package com.ggl.million.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ggl.million.controller.StartButtonListener;
import com.ggl.million.model.MillionGuessesModel;

public class MillionGuessesFrame {
	
	private final JFrame frame;
	
	private final MillionGuessesModel model;
	
	private JButton startButton;
	
	private JTextField numberOfGuessesField;
	
	private final MainPanel mainPanel;
	
	public MillionGuessesFrame(MillionGuessesModel model) {
		this.model = model;
		this.mainPanel = new MainPanel(model);
		this.frame = createAndShowGUI();
	}
	
	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("Million Guesses Game");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exitApplication();
			}
		});
		
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(createControlPanel(), BorderLayout.EAST);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		return frame;
	}
	
	private JPanel createControlPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 5, 5, 5);
		gbc.weightx = 1.0;
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel("Number of guesses:");
		panel.add(label, gbc);
		
		gbc.gridx++;
		numberOfGuessesField = new JTextField(4);
		numberOfGuessesField.setEditable(false);
		panel.add(numberOfGuessesField, gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy++;
		startButton = new JButton("Start Game");
		panel.add(startButton, gbc);
		startButton.addActionListener(new StartButtonListener(this, model));
		
		updateControlPanel();
		
		return panel;
	}
	
	public void updateControlPanel() {
		numberOfGuessesField.setText(
				Integer.toString(model.getTableModel().getRowCount()));
	}
	
	public void exitApplication() {
		frame.dispose();
		System.exit(0);
	}

	public JButton getStartButton() {
		return startButton;
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public JFrame getFrame() {
		return frame;
	}

}
