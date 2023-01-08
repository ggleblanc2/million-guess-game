package com.ggl.million.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ggl.million.model.MillionGuessesModel;

public class TablePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final MillionGuessesModel model;
	
	public TablePanel(MillionGuessesModel model) {
		this.model = model;
		createTablePanel();
	}
	
	private void createTablePanel() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		JTable table = new JTable(model.getTableModel());
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
}
