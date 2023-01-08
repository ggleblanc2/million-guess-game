package com.ggl.million.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.ggl.million.model.MillionGuessesModel;
import com.ggl.million.view.MillionGuessesFrame;

public class WhoGuessesDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private int returnCode;
	
	private JRadioButton computerButton;

//	private final MillionGuessesFrame view;
	
	private final MillionGuessesModel model;

	public WhoGuessesDialog(MillionGuessesFrame view,
			MillionGuessesModel model) {
		super(view.getFrame(), "Who Guesses?", true);
//		this.view = view;
		this.model = model;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.add(createMainPanel(), BorderLayout.CENTER);
		this.add(createButtonPanel(), BorderLayout.SOUTH);
		
		this.pack();
		this.setLocationRelativeTo(view.getFrame());
		this.setVisible(true);
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 5, 5, 5);
		gbc.weightx = 1.0;
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel("Choose who guesses:");
		panel.add(label, gbc);
		
		ButtonGroup group = new ButtonGroup();
		    
		gbc.gridwidth = 1;
		gbc.gridy++;
		JRadioButton playerButton = new JRadioButton("Player");
		playerButton.setSelected(true);
		panel.add(playerButton, gbc);
		group.add(playerButton);
		
		gbc.gridx++;
		computerButton = new JRadioButton("Computer");
		panel.add(computerButton, gbc);
		group.add(computerButton);
		
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		JButton okButton = new JButton("OK");
		panel.add(okButton);
		okButton.addActionListener(event -> {
			model.setComputerGuessing(computerButton.isSelected());
			WhoGuessesDialog.this.dispose();
			returnCode = 0;
		});
		
		JButton cancelButton = new JButton("Cancel");
		panel.add(cancelButton);
		cancelButton.addActionListener(event -> {
			WhoGuessesDialog.this.dispose();
			returnCode = 8;
		});
		
		Dimension d = cancelButton.getPreferredSize();
		d.width += 10;
		okButton.setPreferredSize(d);
		cancelButton.setPreferredSize(d);
		
		return panel;
	}

	public int getReturnCode() {
		return returnCode;
	}

}
