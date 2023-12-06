package actions;

import java.awt.event.ActionEvent;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import gui.FalloutTerminalSolverGUI;

public class InfoButtonAction extends AbstractAction {

	private static final long serialVersionUID = -5773282456463021521L;
	private FalloutTerminalSolverGUI gui;

	public InfoButtonAction(String name, FalloutTerminalSolverGUI falloutTerminalSolverGUI) {
		super(name);
		this.gui = falloutTerminalSolverGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder builder = new StringBuilder();
		builder.append("Selecting the candidate with the highest entropy is your best bet for successfully finding the password.");
		builder.append(System.getProperty("line.separator"));
		builder.append(System.getProperty("line.separator"));
		builder.append(this.gui.getSolver().getCalc().getWordlist().size()).append(" candidates left:");
		builder.append(System.getProperty("line.separator"));
		for (String candidate : this.gui.getSolver().getCalc().getWordlist()) {
			builder.append(String.format("%s \t %.4f", candidate, this.gui.getSolver().getCalc().calculateEntropy(candidate)));
			builder.append(System.getProperty("line.separator"));
		}
		builder.append("Best choice is: ").append(this.gui.getSolver().getCalc().getBestChoice());
		builder.append(System.getProperty("line.separator"));
		builder.append(System.getProperty("line.separator"));
		builder.append("The positional occurrance map looks like this:");
		builder.append(System.getProperty("line.separator"));
		for (int i = 0; i < this.gui.getSolver().getCalc().getOccurrancesAtPosition().size(); i++) {
			builder.append(i+1).append(": ");
			builder.append(Collections.singletonList(this.gui.getSolver().getCalc().getOccurrancesAtPosition().get(i)));
			builder.append(System.getProperty("line.separator"));
		}
		
		
		JOptionPane.showMessageDialog(gui.getFrame(), builder.toString(), "Entropy Info", JOptionPane.INFORMATION_MESSAGE);
	}

}
