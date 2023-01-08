package com.ggl.million.model;

import javax.swing.table.DefaultTableModel;

public class MillionGuessesModel {
	
	private boolean isComputerGuessing;
	
	private final DefaultTableModel tableModel;
	
	private String numberToGuess;

	public MillionGuessesModel() {
		this.tableModel = createTableModel();
	}
	
	private DefaultTableModel createTableModel() {
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Guess");
		tableModel.addColumn("Correct Position");
		tableModel.addColumn("Correct Digits");
		
		return tableModel;
	}
	
	public void resetGuesses() {
		for (int index = tableModel.getRowCount() - 1; index >= 0; index--) {
			tableModel.removeRow(index);
		}
	}
	
	public void addGuess(Guess guess) {
		Object[] rowData = new Object[tableModel.getColumnCount()];
		rowData[0] = guess.getGuess();
		rowData[1] = guess.getCorrectPosition();
		rowData[2] = guess.getCorrectDigits();
		tableModel.insertRow(0, rowData);;
	}

	public boolean isComputerGuessing() {
		return isComputerGuessing;
	}

	public void setComputerGuessing(boolean isComputerGuessing) {
		this.isComputerGuessing = isComputerGuessing;
	}

	public String getNumberToGuess() {
		return numberToGuess;
	}

	public void setNumberToGuess(String numberToGuess) {
		this.numberToGuess = numberToGuess;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
}
