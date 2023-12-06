package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import actions.HitFeedbackButtonAction;
import actions.InfoButtonAction;
import actions.StartButtonAction;
import core.TerminalSolver;
import javax.swing.JButton;
import javax.swing.DefaultListModel;


public class FalloutTerminalSolverGUI {

	private JFrame frame;
	private JTextField textFieldWordEntry;
	private JList<String> listWordlist;
	private JButton buttonHitFeedback3;
	private JButton buttonHitFeedback2;
	private JButton buttonHitFeedback1;
	private JButton buttonHitFeedback4;
	private JButton buttonHitFeedback5;
	private JButton buttonHitFeedback6;
	private JButton buttonHitFeedback7;
	private JButton buttonHitFeedback8;
	private JButton buttonHitFeedback9;
	private JButton buttonHitFeedback10;
	private JButton buttonHitFeedback11;
	private JButton btnStart;
	private TerminalSolver solver;
	private List<JButton> hitFeedbackButtons;
	private JButton buttonHitFeedback0;
	private JButton btnInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FalloutTerminalSolverGUI window = new FalloutTerminalSolverGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FalloutTerminalSolverGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.hitFeedbackButtons = new ArrayList<JButton>();
		frame = new JFrame();
		frame.setBounds(100, 100, 584, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][grow][]"));
		
		JLabel lblEnterPossibleCandidates = new JLabel("Enter possible candidates seperated by a space:");
		lblEnterPossibleCandidates.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblEnterPossibleCandidates, "cell 0 0");
		
		textFieldWordEntry = new JTextField();
		textFieldWordEntry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(textFieldWordEntry, "cell 0 1,growx");
		textFieldWordEntry.setColumns(10);
		
		btnStart = new JButton(new StartButtonAction("Start", this));
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnStart, "flowx,cell 0 2");
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "cell 0 3,grow");
		
		listWordlist = new JList<String>(new DefaultListModel<String>());
		listWordlist.setFont(new Font("Tahoma", Font.BOLD, 16));
		listWordlist.setVisibleRowCount(12);
		listWordlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listWordlist);
		
		buttonHitFeedback0 = new JButton(new HitFeedbackButtonAction(0, this));
		buttonHitFeedback0.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback0, "flowx,cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback0);
		
		buttonHitFeedback1 = new JButton(new HitFeedbackButtonAction(1, this));
		buttonHitFeedback1.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback1, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback1);
		
		buttonHitFeedback2 = new JButton(new HitFeedbackButtonAction(2, this));
		buttonHitFeedback2.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback2, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback2);
		
		buttonHitFeedback3 = new JButton(new HitFeedbackButtonAction(3, this));
		buttonHitFeedback3.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback3, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback3);
		
		buttonHitFeedback4 = new JButton(new HitFeedbackButtonAction(4, this));
		buttonHitFeedback4.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback4, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback4);
		
		buttonHitFeedback5 = new JButton(new HitFeedbackButtonAction(5, this));
		buttonHitFeedback5.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback5, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback5);
		
		buttonHitFeedback6 =  new JButton(new HitFeedbackButtonAction(6, this));
		buttonHitFeedback6.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback6, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback6);
		
		buttonHitFeedback7 = new JButton(new HitFeedbackButtonAction(7, this));
		buttonHitFeedback7.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback7, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback7);
		
		buttonHitFeedback8 = new JButton(new HitFeedbackButtonAction(8, this));
		buttonHitFeedback8.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback8, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback8);
		
		buttonHitFeedback9 =  new JButton(new HitFeedbackButtonAction(9, this));
		buttonHitFeedback9.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback9, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback9);
		
		buttonHitFeedback10 =  new JButton(new HitFeedbackButtonAction(10, this));
		buttonHitFeedback10.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback10, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback10);
		
		buttonHitFeedback11 =  new JButton(new HitFeedbackButtonAction(11, this));
		buttonHitFeedback11.setEnabled(false);
		frame.getContentPane().add(buttonHitFeedback11, "cell 0 4");
		this.hitFeedbackButtons.add(buttonHitFeedback11);
		
		btnInfo = new JButton(new InfoButtonAction("Info", this));
		btnInfo.setEnabled(false);
		btnInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(btnInfo, "cell 0 2");
	}

	public JTextField getTextFieldWordEntry() {
		return textFieldWordEntry;
	}

	public JList<String> getListWordlist() {
		return listWordlist;
	}

	public TerminalSolver getSolver() {
		return solver;
	}

	public List<JButton> getHitFeedbackButtons() {
		return hitFeedbackButtons;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getBtnInfo() {
		return btnInfo;
	}

	public void setSolver(TerminalSolver solver) {
		this.solver = solver;
	}
}
