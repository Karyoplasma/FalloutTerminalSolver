package actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import gui.FalloutTerminalSolverGUI;

public class HitFeedbackButtonAction extends AbstractAction {

	private static final long serialVersionUID = -1810180803438046985L;
	private FalloutTerminalSolverGUI gui;
	private int feedback;
	
	public HitFeedbackButtonAction(int feedback, FalloutTerminalSolverGUI falloutTerminalSolverGUI) {
		super(Integer.toString(feedback));
		this.feedback = feedback;
		this.gui = falloutTerminalSolverGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String choice = this.gui.getListWordlist().getSelectedValue();
		
		for (String removedCandidate : this.gui.getSolver().removeImpossibles(choice, feedback)) {
			((DefaultListModel<String>) this.gui.getListWordlist().getModel()).removeElement(removedCandidate);
		};
		this.gui.getListWordlist().setSelectedIndex(this.gui.getSolver().getWordlist().indexOf(this.gui.getSolver().getCalc().getBestChoice()));
	}

}
