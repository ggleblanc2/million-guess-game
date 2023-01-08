package com.ggl.million.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.ggl.million.model.MillionGuessesModel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final CardLayout cardLayout;
	
	private final ImagePanel imagePanel;
	
//	private final MillionGuessesModel model;
	
	private final TablePanel tablePanel;

	public MainPanel(MillionGuessesModel model) {
//		this.model = model;
		this.tablePanel = new TablePanel(model);
		this.imagePanel = new ImagePanel(model, tablePanel.getPreferredSize());
		
		this.cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		this.add(tablePanel, "table");
		this.add(imagePanel, "image");
	}

	public void showTablePanel() {
		cardLayout.show(this, "table");
	}
	
	public void showImagePanel() {
		cardLayout.show(this, "image");
	}
}
