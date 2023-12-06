package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import core.TerminalSolver;
import core.WordLengthException;
import gui.FalloutTerminalSolverGUI;

public class StartButtonAction extends AbstractAction {

	private static final long serialVersionUID = -1176583036408133383L;
	private FalloutTerminalSolverGUI gui;
	
	public StartButtonAction(String name, FalloutTerminalSolverGUI falloutTerminalSolverGUI) {
		super(name);
		this.gui = falloutTerminalSolverGUI;
	}
	
	public void disableHitFeedback(int passwordLength) {
		for (int i = 0; i < this.gui.getHitFeedbackButtons().size(); i++) {
			if (i < passwordLength) {
				this.gui.getHitFeedbackButtons().get(i).setEnabled(true);
			} else {
				this.gui.getHitFeedbackButtons().get(i).setEnabled(false);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.gui.getBtnStart().getText().equals("Start")) {
			this.startSolving();
		} else {
			this.reset();
		}
	}

	private void reset() {
		DefaultListModel<String> listModel = (DefaultListModel<String>) this.gui.getListWordlist().getModel();
		listModel.removeAllElements();
		this.gui.getBtnStart().setText("Start");
		this.disableHitFeedback(-1);
		this.gui.getBtnInfo().setEnabled(false);
	}

	private void startSolving() {
		String input = this.gui.getTextFieldWordEntry().getText();
		List<String> wordlist= new ArrayList<String>();
		
		if (input.isEmpty()) {
			JOptionPane.showMessageDialog(this.gui.getFrame(), "Please enter candidate passwords!", "Text field is empty", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String[] words = this.gui.getTextFieldWordEntry().getText().split("\\s");
		for (String word : words) {
			wordlist.add(word.toUpperCase());
		}
		try {
			this.gui.setSolver(new TerminalSolver(wordlist));
		} catch (WordLengthException e) {
			JOptionPane.showMessageDialog(this.gui.getFrame(), e.getMessage(), e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
			return;
		}
		DefaultListModel<String> listModel = (DefaultListModel<String>) this.gui.getListWordlist().getModel();
		for (String candidate : this.gui.getSolver().getWordlist()) {
			listModel.addElement(candidate);
		}
		this.gui.getListWordlist().setSelectedIndex(this.gui.getSolver().getWordlist().indexOf(this.gui.getSolver().getCalc().getBestChoice()));
		this.disableHitFeedback(this.gui.getSolver().getWordlist().get(0).length());
		this.gui.getBtnInfo().setEnabled(true);
		this.gui.getBtnStart().setText("Restart");
	}

}
