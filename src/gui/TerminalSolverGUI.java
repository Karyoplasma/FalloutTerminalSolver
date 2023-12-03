package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.TerminalSolver;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

public class TerminalSolverGUI implements ActionListener{

	private JFrame frmFalloutTerminalSolver;
	private JTextField textFieldhitFeedback;
	private JTextArea textAreaWordlist;
	private JButton btnStart;
	private JButton btnUpdate;
	private TerminalSolver solver;
	private JSpinner spinnerChoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TerminalSolverGUI window = new TerminalSolverGUI();
					window.frmFalloutTerminalSolver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TerminalSolverGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFalloutTerminalSolver = new JFrame();
		frmFalloutTerminalSolver.setTitle("Fallout Terminal Solver");
		frmFalloutTerminalSolver.setBounds(100, 100, 450, 300);
		frmFalloutTerminalSolver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFalloutTerminalSolver.getContentPane().setLayout(new MigLayout("", "[][grow][grow]", "[][grow][][]"));
		
		JLabel lblEnterPossiblePasswords = new JLabel("Enter possible passwords:");
		lblEnterPossiblePasswords.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmFalloutTerminalSolver.getContentPane().add(lblEnterPossiblePasswords, "cell 0 0 3 1");
		
		JScrollPane scrollPaneWordlist = new JScrollPane();
		frmFalloutTerminalSolver.getContentPane().add(scrollPaneWordlist, "cell 1 1 2 1,grow");
		
		textAreaWordlist = new JTextArea();
		textAreaWordlist.setFont(new Font("Monospaced", Font.PLAIN, 13));
		scrollPaneWordlist.setViewportView(textAreaWordlist);
		
		spinnerChoice = new JSpinner();
		spinnerChoice.setModel(new SpinnerListModel(new String[] {"Select choice here:"}));
		spinnerChoice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmFalloutTerminalSolver.getContentPane().add(spinnerChoice, "cell 1 2");
		
		textFieldhitFeedback = new JTextField();
		textFieldhitFeedback.setEditable(false);
		textFieldhitFeedback.setEnabled(false);
		frmFalloutTerminalSolver.getContentPane().add(textFieldhitFeedback, "cell 2 2,growx");
		textFieldhitFeedback.setColumns(10);
		
		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnStart.addActionListener(this);
		frmFalloutTerminalSolver.getContentPane().add(btnStart, "cell 1 3");
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.addActionListener(this);
		frmFalloutTerminalSolver.getContentPane().add(btnUpdate, "cell 2 3");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			if (this.btnStart.getText().equals("Start")){
				this.startSolving();
			} else {
				this.resetSolver();
			}
			
		}
		if (e.getSource() == btnUpdate) {
			this.updateWordlist();
		}
		
	}

	private void resetSolver() {
		this.textAreaWordlist.setText("");
		this.btnStart.setText("Start");
		this.spinnerChoice.setModel(new SpinnerListModel(new String[] {"Best choice"}));
		this.textFieldhitFeedback.setText("");
		this.textFieldhitFeedback.setEnabled(false);
		this.textFieldhitFeedback.setEditable(false);
		this.btnUpdate.setEnabled(false);
		
	}

	private void updateWordlist() {
		int hitFeedback = Integer.parseInt(this.textFieldhitFeedback.getText());
		String choice = (String) this.spinnerChoice.getModel().getValue();
		
		solver.removeImpossibles(choice, hitFeedback);
		this.spinnerChoice.setModel(solver.getSpinnerModel());
		this.textAreaWordlist.setText(this.solver.getWordlistString());
		
	}

	private void startSolving() {
		String[] words = this.textAreaWordlist.getText().split("\\s");
		List<String> wordlist= new ArrayList<String>();
		for (String word : words) {
			wordlist.add(word.toUpperCase());
		}
		try {
			this.solver = new TerminalSolver(wordlist);
		} catch (Exception e) {
			this.textAreaWordlist.setText(e.toString());
			e.printStackTrace();
			return;
		}
		this.spinnerChoice.setModel(solver.getSpinnerModel());
		this.spinnerChoice.getModel().setValue(solver.getCalc().getBestChoice());
		this.textAreaWordlist.setText(this.solver.getWordlistString());
		this.textFieldhitFeedback.setText("");
		this.textFieldhitFeedback.setEnabled(true);
		this.textFieldhitFeedback.setEditable(true);
		this.btnStart.setText("Restart");
		this.btnUpdate.setEnabled(true);
	}

}
