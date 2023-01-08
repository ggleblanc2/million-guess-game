package com.ggl.million.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.ggl.million.view.MillionGuessesFrame;

public class ComputerGuessDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private int returnCode;
	
	private final String displayString;
	
	public ComputerGuessDialog(MillionGuessesFrame view, String displayString) {
		super(view.getFrame(), "Computer Guesses", true);
		this.displayString = displayString;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.add(createMainPanel(), BorderLayout.CENTER);
		this.add(createButtonPanel(), BorderLayout.SOUTH);
		
		this.pack();
		this.setLocationRelativeTo(view.getFrame());
		this.setVisible(true);
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
		
		panel.add(Box.createVerticalStrut(5));
		
		JLabel label = new JLabel(displayString);
		panel.add(label);
		
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		JButton okButton = new JButton("OK");
		panel.add(okButton);
		okButton.addActionListener(event -> {
			processOK();
		});
		
		JButton cancelButton = new JButton("Cancel");
		panel.add(cancelButton);
		cancelButton.addActionListener(event -> {
			ComputerGuessDialog.this.dispose();
			returnCode = 8;
		});
		
		Dimension d = cancelButton.getPreferredSize();
		d.width += 10;
		okButton.setPreferredSize(d);
		cancelButton.setPreferredSize(d);
		
		setKeyBindings(panel);
		
		return panel;
	}
	
	private void setKeyBindings(JPanel panel) {
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = panel.getActionMap();
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
		
		actionMap.put("enter", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent event) {
				processOK();
			}
		});
		
		actionMap.put("escape", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent event) {
				ComputerGuessDialog.this.dispose();
				returnCode = 8;
			}
		});
	}
	
	private void processOK() {
		this.dispose();
		returnCode = 0;
	}

	public int getReturnCode() {
		return returnCode;
	}

}
