package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntropyCalculator {
	
	private List<String> wordlist;
	private int length;
	private Map<Integer, Map<String, Integer>> occurrancesAtPosition;
	
	public EntropyCalculator(int length) {
		wordlist = new ArrayList<String>();
		this.length = length;
	}
	
	public EntropyCalculator(List<String> wordlist) throws WordLengthException {
		this.length = wordlist.get(0).length();
		this.wordlist = new ArrayList<String>();
		for (String word : wordlist) {
			this.add(word);
		}
	}
	
	public void add(String word) throws WordLengthException {
		if (word.length() != this.length) {
			throw new WordLengthException();
		}
		word = word.toUpperCase();
		this.wordlist.add(word);
		this.updateOccurrancesOnAdd(word);
	}
	
	public void remove(String word) {
		word = word.toUpperCase();
		if (this.wordlist.contains(word)) {
			this.wordlist.remove(word);
			this.updateOccurrancesOnRemove(word);
		}
	}

	private void updateOccurrancesOnRemove(String word) {
		for (int i = 0; i < this.length; i++) {
			Map<String, Integer> temp = this.occurrancesAtPosition.get(i);
			int occurrances = temp.get(word.substring(i, i+1)) - 1;
			if (occurrances == 0) {
				temp.remove(word.substring(i, i+1));
			} else {
				temp.replace(word.substring(i, i+1), occurrances);
			}
		}
	}

	private void updateOccurrancesOnAdd(String word) {
		if (this.occurrancesAtPosition == null) {
			this.occurrancesAtPosition = new HashMap<Integer, Map<String, Integer>>();
			for (int i = 0; i < this.length; i++) {
				Map<String, Integer> temp = new HashMap<String, Integer>();
				temp.put(word.substring(i, i+1), 1);
				this.occurrancesAtPosition.put(i, temp);
			}
			return;
		}
		for (int j = 0; j < this.length; j++) {
			Map<String, Integer> positionMap = this.occurrancesAtPosition.get(j);
			if (this.occurrancesAtPosition.get(j).containsKey(word.substring(j, j+1))) {
				int occurrances = positionMap.get(word.substring(j, j+1));
				occurrances++;
				positionMap.replace(word.substring(j, j+1), occurrances);
			} else {
				positionMap.put(word.substring(j, j+1), 1);
				//this.occurrancesAtPosition.replace(j, positionMap);
			}
		}
	}
	
	public double calculateEntropy(String word) {
		double temp = 0;
		for (int i = 0; i < length; i ++) {
			double px = this.occurrancesAtPosition.get(i).get(word.substring(i, i+1)) / (double) this.wordlist.size();
			temp += px * (Math.log10(px) / Math.log10(2));
		}
		return 0-temp;
	}
	
	public String getBestChoice() {
		String bestChoice = "";
		Double highestEntropy = -1D;
		
		for (String word : this.wordlist) {
			Double currentEntropy = this.calculateEntropy(word);
			if (currentEntropy > highestEntropy) {
				highestEntropy = currentEntropy;
				bestChoice = word;
			}
		}
		
		return bestChoice;
	}
	
	public static void main(String[] args) throws WordLengthException {
		EntropyCalculator e = new EntropyCalculator(5);
		e.add("tests");
		e.add("bests");
		e.add("rests");
		e.add("betas");
		e.add("idiot");
		e.add("meats");
		e.add("feats");
		e.add("fetas");
		e.add("beats");
		System.out.println(e.occurrancesAtPosition);
		for (String word : e.wordlist) {
			System.out.println(String.format("%s: %.4f", word, e.calculateEntropy(word)));
		}
	
	}
}
