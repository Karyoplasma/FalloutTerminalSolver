package core;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SpinnerListModel;


public class TerminalSolver {
	List<String> wordlist;
	EntropyCalculator calc;
	
	public TerminalSolver(List<String> wordlist) throws WordLengthException {
		this.wordlist = wordlist;
		this.calc = new EntropyCalculator(wordlist);
	}

	public void removeImpossibles(String choice, int hitFeedback) {
		List<String> removals = new ArrayList<String>();
		for (String word : wordlist) {
			if (this.getHits(choice, word) != hitFeedback) {
				removals.add(word);
				calc.remove(word);
			}
		}
		this.wordlist.removeAll(removals);
	}

	private int getHits(String choice, String word) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if (choice.charAt(i) == word.charAt(i)) {
				count++;
			}
		}
		return count;
	}
	
	public EntropyCalculator getCalc() {
		return this.calc;
	}

	public String getWordlistString() {
		StringBuilder builder = new StringBuilder();
		for (String word : this.wordlist) {
			builder.append(word).append(System.getProperty("line.separator"));
		}
		return builder.toString();
	}

	public SpinnerListModel getSpinnerModel() {
		return new SpinnerListModel(this.wordlist.toArray());
	}
}
